package fr.isen.ari.androiderestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.ari.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.titleCategory.text = intent.getStringExtra("category") ?: ""

        val dishes = arrayListOf<String>()
        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)
        binding.recyclerCategory.adapter = CustomAdapter(dishes)
    }

}