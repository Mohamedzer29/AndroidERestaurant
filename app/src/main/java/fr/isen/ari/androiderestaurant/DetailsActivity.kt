package fr.isen.ari.androiderestaurant

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.ari.androiderestaurant.databinding.ActivityDetailsBinding
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

@Suppress("DEPRECATION")
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dish: Items
    private var quantity:Float = 0.0F
    private var absTopSubMenus: Menu? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        absTopSubMenus = menu
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dish = intent.extras?.getSerializable("item") as Items
        binding.DishTitle.text = dish.nameFr

        for (i in dish.ingredients) {
            binding.IngredientsText.text = binding.IngredientsText.text.toString() + i.nameFr + ", "
        }

        binding.ButtonPlus.setOnClickListener() {
            clickOnButtonPlus()
            refreshTotalPrice()
        }

        binding.ButtonMinus.setOnClickListener {
            clickOnButtonMinus()
            refreshTotalPrice()
        }

        binding.ButtonTotal.setOnClickListener {
            refreshTotalPrice()
            addToJSON()
        }



        val viewPager = binding.viewPager2
        val adapter = CategoryDishAdapter(dish.images)

        viewPager.adapter = adapter

    }
    @SuppressLint("SetTextI18n")
    fun clickOnButtonPlus() {
        quantity++
        binding.QuantityDish.text = quantity.toString()
    }

    private fun clickOnButtonMinus() {
        if (quantity > 0) {
            quantity--
            binding.QuantityDish.text = (quantity).toString()
        }
        else {
            binding.QuantityDish.text = "0"
        }
    }

    @SuppressLint("SetTextI18n")
    fun refreshTotalPrice() {
        binding.ButtonTotal.text = "Total : " + (quantity * (dish.prices[0].price?.toFloat()!!)).toString() + " €"
    }

    @SuppressLint("SetTextI18n")
    private fun addToJSON() {
        val file = File(filesDir, "cart.json")
        val jsonCart = JSONObject()
        jsonCart.put("name", dish.nameFr)
        val price = quantity * (dish.prices[0].price?.toFloat()!!)
        jsonCart.put("price", price)
        var jsonString = jsonCart.toString()
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(jsonString.toByteArray())
        fileOutputStream.close()

        Snackbar.make(binding.root, "Commande prise en compte", Snackbar.LENGTH_LONG).show()
        val fileInputStream = FileInputStream(file)
        var jsonStringInput = Scanner(fileInputStream).useDelimiter("\\A").next()
        val jsonObject = JSONObject(jsonStringInput)
        val count = jsonObject.length()
        fileInputStream.close()
        val quantityRound = findViewById<TextView>(R.id.quantity_round)
        quantityRound.text = (count).toString()
        quantityRound.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}

