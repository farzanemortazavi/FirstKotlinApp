package com.example.firstkotlinapp.koin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.mvp.Timings
import kotlinx.android.synthetic.main.activity_koin_mvvm.*
import java.text.SimpleDateFormat
import java.util.*

class koinMVVMActivity : AppCompatActivity() {

    private lateinit var myViewModel: koinViewModel
    private lateinit var mainHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin_mvvm)

        myViewModel = ViewModelProvider(this).get(koinViewModel::class.java)

        btnKoinPrayerTime.setOnClickListener {
            myViewModel.onPrayerButtonClicked(getCountryCity().first, getCountryCity().second)
        }

        myViewModel.getResponse().observe(this,androidx.lifecycle.Observer {
            showPrayerTime(it.data.timings)
        })

        myViewModel.getError().observe(this,androidx.lifecycle.Observer {
            Toast.makeText(this,it, Toast.LENGTH_SHORT)
        })

        mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(updateTextTask)

    }
    //************************************************************
    fun getCountryCity(): Pair<String, String> {
        val country = edtKoinCountry.text.trim().toString()
        val city = edtKoinCity.text.trim().toString()
        return country to city
    }
    //***********************************************************
    fun showPrayerTime(prayerTimings: Timings?) {
        txtKoinFajr.text =  prayerTimings?.Fajr
        txtKoinSunrise.text = prayerTimings?.Sunrise
        txtKoinDhuhur.text = prayerTimings?.Dhuhr
        txtKoinSunset.text =  prayerTimings?.Sunset
        txtKoinMidnight.text = prayerTimings?.Midnight
    }
    //**********************************************************
    private val updateTextTask = object : Runnable {
        override fun run() {
            showUserTime()
            mainHandler.postDelayed(this, 1000)
        }
    }
    //*********************************************************
    fun showUserTime() {

        Log.d("myTest","in ShowUserTime Method")

        val currentTime = Calendar.getInstance().time
        val df = SimpleDateFormat("HH:mm")
        Log.d("myTest","user time: " + df.format(currentTime))
        txtKoinUserTime.text = "user time: " + df.format(currentTime)
        val partOfDay=myViewModel.getPartsofDayByHour(df.format(currentTime).dropLast(3))

        when(partOfDay){
            "night"-> imgKoinTime.setBackgroundResource(R.drawable.night)
            "sunset"-> imgKoinTime.setBackgroundResource(R.drawable.sunset)
            "noon"->imgKoinTime.setBackgroundResource(R.drawable.noon)
            else -> imgKoinTime.setBackgroundResource(R.drawable.morning)
        }

    }


}
