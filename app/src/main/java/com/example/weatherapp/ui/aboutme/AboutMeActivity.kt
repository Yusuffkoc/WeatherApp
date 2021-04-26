package com.example.weatherapp.ui.aboutme

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityAboutmeBinding
import com.google.gson.Gson


class AboutMeActivity  : AppCompatActivity() {

    lateinit var binding: ActivityAboutmeBinding

    val gson: Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding= ActivityAboutmeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myPhoneNumber = getString(R.string.my_phone)
        val myEmail = getString(R.string.my_email)

        binding.txtEmail.text = getString(R.string.email,myEmail)
        binding.txtPhone.text = getString(R.string.phone,myPhoneNumber)

        binding.txtEmail.setOnClickListener {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_APP_EMAIL)
                intent.putExtra(Intent.EXTRA_EMAIL, myEmail)
                startActivity(intent)
        }
        binding.txtPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("+90$myPhoneNumber")
            startActivity(intent)
        }

    }
}
