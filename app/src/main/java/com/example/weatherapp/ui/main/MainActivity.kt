package com.example.weatherapp.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.models.ResponseModel
import com.example.weatherapp.ui.aboutme.AboutMeActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val CITY: String = "Istanbul,tr"
    val API: String = "1a04188da0eec086100068b37e4ba94c"

    val gson:Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        weatherTask().execute()
        binding.swipeLayout.setOnRefreshListener {
            weatherTask().execute()
        }

        btninfo.setOnClickListener {
            val intent= Intent(this, AboutMeActivity::class.java)
            startActivity(intent)
        }

    }

    inner class  weatherTask(): AsyncTask<String, Void, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            binding.loader.visibility= View.VISIBLE
            binding.mainContainer.visibility=View.GONE
            binding.errortext.visibility=View.GONE

        }

        override fun doInBackground(vararg  p0: String?) : String?
        {
            var response: String?
            try {

                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                    .readText(Charsets.UTF_8)

            }
            catch (e:Exception)
            {
                response= null
            }

            return response

        }

        @SuppressLint("SetTextI18n")
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            binding.swipeLayout.isRefreshing=false
            try {
                val myData: ResponseModel = gson.fromJson(result,ResponseModel::class.java)

                binding.adress.text = "${myData.name}, ${myData.sys.country}"
                binding.updateAt.text = "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(myData.dt*1000))
                binding.status.text = myData.weather.getOrNull(0)?.description
                binding.temp.text = myData.main.temp.toString() + "°C"
                binding.tempMin.text = "Min temp: " +myData.main.tempMin+ "°C"
                binding.tempMax.text = "Max temp: " +myData.main.tempMax+ "°C"
                binding.sunrise.text = SimpleDateFormat("hh:mm a",Locale.ENGLISH).format(Date(myData.sys.sunrise * 1000))
                binding.sunset.text =  SimpleDateFormat("hh:mm a",Locale.ENGLISH).format(Date(myData.sys.sunset*1000))
                binding.wind.text = myData.wind.speed.toString()
                binding.pressure.text = myData.main.pressure.toString()
                binding.humidity.text = myData.main.humidity.toString()

                binding.loader.visibility = View.GONE
                binding.mainContainer.visibility = View.VISIBLE

            }
            catch (e:Exception)
            {
                binding.loader.visibility = View.GONE
                binding.errortext.visibility=View.VISIBLE
            }
        }
    }

}
