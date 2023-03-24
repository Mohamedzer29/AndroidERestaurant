package fr.isen.ari.androiderestaurant

import com.google.gson.annotations.SerializedName


data class FoodDataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

) : java.io.Serializable