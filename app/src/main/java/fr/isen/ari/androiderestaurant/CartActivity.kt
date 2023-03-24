package fr.isen.ari.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.ari.androiderestaurant.databinding.ActivityCartBinding
import fr.isen.ari.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.util.*

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerCategoryCart

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = CustomAdapter(arrayListOf()){
            val intent = Intent(this@CartActivity, DetailsActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
        loadDishesFromAPI()
    }
    private fun loadDishesFromAPI() {
        val file = File(filesDir, "cart.json")
        val fileInputStream = FileInputStream(file)
        var jsonStringInput = Scanner(fileInputStream).useDelimiter("\\A").next()
        handleAPIData(jsonStringInput)
    }
    private fun handleAPIData(data: String) {
        val dishesResult = Gson().fromJson(data, FoodDataResult::class.java)
        val dishes = dishesResult.data.firstOrNull {
            it.nameFr == (intent.extras?.getString("titleCategory") ?: "No title available")
        }

        val adapter = binding.recyclerCategoryCart.adapter as CustomAdapter
        if (dishes != null) {
            adapter.refreshList(dishes.items)
        }
    }
}