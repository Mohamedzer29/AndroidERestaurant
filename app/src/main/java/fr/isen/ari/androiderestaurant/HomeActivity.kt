package fr.isen.ari.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import fr.isen.ari.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val starters = findViewById<Button>(R.id.starters)

        binding.starters.setOnClickListener {
            Toast.makeText(this, "Entrées", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StartersActivity::class.java)
            intent.putExtra("categorie", "entrées")
            startActivity(intent)
        }
       // val meals = findViewById<Button>(R.id.meals)

        binding.meals.setOnClickListener {
            Toast.makeText(this, "Repas", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MealsActivity::class.java)
            intent.putExtra("categorie", "plats")
            startActivity(intent)
        }

        //val desert = findViewById<Button>(R.id.desert)

        binding.desert.setOnClickListener {
            Toast.makeText(this, "Desserts", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DesertsActivity::class.java)
            intent.putExtra("categorie", "desserts")
            startActivity(intent)
        }
    }

}

