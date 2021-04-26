package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    val temp : Double ,
    @SerializedName("feels_like")
    val feelsLike :Double,
    @SerializedName("temp_min")
    val tempMin : Int ,
    @SerializedName("temp_max")
    val tempMax :Int,
    @SerializedName("pressure")
    val pressure : Int ,
    @SerializedName("humidity")
    val humidity :Int
)