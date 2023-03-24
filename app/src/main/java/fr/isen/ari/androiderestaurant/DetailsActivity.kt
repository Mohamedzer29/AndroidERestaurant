package fr.isen.ari.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.ari.androiderestaurant.databinding.ActivityDetailsBinding
@Suppress("DEPRECATION")
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dish: Items
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_details)

        dish = intent.extras?.getSerializable("item") as Items
        binding.DishTitle.text = dish.nameFr

        for (i in dish.ingredients) {
            binding.IngredientsText.text = binding.IngredientsText.text.toString() + i.nameFr + ", "
        }

        binding.btn_plus.setOnClickListener {

        }

        binding.btn_minus.setOnClickListener {

        }

        binding.Total_btn.setOnClickListener {

        }

        val viewPager = binding.viewPager2
        val adapter = CustomAdapter(dish.images)

        viewPager.adapter = adapter

    }
}