package fr.isen.ari.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val plat = intent.getStringExtra("plat")

        val textView = findViewById<TextView>(R.id.dishName)
        textView.text = "Détails du plat : $plat"
    }
}