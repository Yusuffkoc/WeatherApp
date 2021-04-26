package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("coord")
    val coord : Coord,
    @SerializedName("weather")
    val weather : ArrayList<Weather>,
    @SerializedName("base")
    val base : String,
    @SerializedName("main")
    val main : Main,
    @SerializedName("visibility")
    val visibility : Long,
    @SerializedName("wind")
    val wind : Wind,
    @SerializedName("clouds")
    val clouds : Clouds,
    @SerializedName("dt")
    val dt : Long,
    @SerializedName("sys")
    val sys : Sys,
    @SerializedName("timezone")
    val timezone : Long,
    @SerializedName("id")
    val id : Long,
    @SerializedName("name")
    val name : String,
    @SerializedName("cod")
    val cod : Int
)