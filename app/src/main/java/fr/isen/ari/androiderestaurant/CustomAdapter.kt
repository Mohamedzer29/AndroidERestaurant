package fr.isen.ari.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(private var dishes: ArrayList<Items>, val onItemClickListener: (DishTitle: Items) -> Unit) : RecyclerView.Adapter<CustomAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dishName: TextView = itemView.findViewById(R.id.dishName)
        val price: TextView = itemView.findViewById(R.id.priceView)
        val images: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_cell, parent, false)
        print(dishes)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish.nameFr

        holder.itemView.setOnClickListener {
            onItemClickListener(dish)
        }

        holder.price.text = dish.prices[0].price + "$"

        val foodImage = dish.images[0]
        if (foodImage.isNotEmpty()) {
            Picasso.get().load(foodImage).resize(150, 150).into(holder.images)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(dishesFromAPI: ArrayList<Items>) {
        dishes = dishesFromAPI
        notifyDataSetChanged()
    }
}