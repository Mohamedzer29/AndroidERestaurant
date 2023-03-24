package fr.isen.ari.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.ari.androiderestaurant.databinding.ActivityDetailsBinding
import org.json.JSONObject
import java.io.File

@Suppress("DEPRECATION")
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dish: Items

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

        binding.btnPlus.setOnClickListener() {
            clickOnButtonPlus()
            refreshTotalPrice()
        }

        binding.btnMinus.setOnClickListener {
            refreshTotalPrice()
            addToJSON()
        }

        binding.TotalBtn.setOnClickListener {
            refreshTotalPrice()
            addToJSON()
        }

        val viewPager = binding.viewPager2
        val adapter = CategoryDishAdapter(dish.images)

        viewPager.adapter = adapter

    }
    @SuppressLint("SetTextI18n")
    fun clickOnButtonPlus() {
        binding.QuantityDish.text = (binding.QuantityDish.text.toString().toInt() + 1).toString()
    }

    private fun clickOnButtonMinus() {
        if (binding.QuantityDish.text.toString().toInt() > 0) {
            binding.QuantityDish.text = (binding.QuantityDish.text.toString().toInt() - 1).toString()
        }
        else {
            binding.QuantityDish.text = "0"
        }
    }

    @SuppressLint("SetTextI18n")
    fun refreshTotalPrice() {
        binding.TotalBtn.text = "Total : " + (binding.QuantityDish.text.toString().toInt() * (dish.prices[0].price?.toInt()
            ?: 999)).toString() + " €"
    }

    private fun addToJSON() {
        val file = File(filesDir, "cart.json")
        val jsonCart = JSONObject()
        jsonCart.put("name", dish.nameFr)
        val price = binding.QuantityDish.text.toString().toInt() * (dish.prices[0].price?.toInt()?: 999)
        jsonCart.put("price", price)
        val cart = GsonBuilder().setPrettyPrinting().create().toJson(jsonCart)
        file.writeText(cart)

        val notif = Snackbar.make(binding.root, "Commande mise à jour", Snackbar.LENGTH_LONG)
        notif.show()
    }
}
