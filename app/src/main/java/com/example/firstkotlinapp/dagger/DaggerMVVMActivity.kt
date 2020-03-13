package com.example.firstkotlinapp.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.mvp.Timings
import com.example.firstkotlinapp.mvvm.ViewModel
import kotlinx.android.synthetic.main.activity_dagger_mvvm.*
import java.text.SimpleDateFormat
import java.util.*

class DaggerMVVMActivity : AppCompatActivity() {

    private lateinit var myViewModel: daggerViewModel
    private lateinit var mainHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger_mvvm)

        val factory=DaggerfactoryComponent.create().provideVMFactory()
        myViewModel = ViewModelProvider(this, factory).get(daggerViewModel::class.java)

        btnDaggerPrayerTime.setOnClickListener {
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
        val country = edtDaggerCountry.text.trim().toString()
        val city = edtDaggerCity.text.trim().toString()
        return country to city
    }
    //***********************************************************
    fun showPrayerTime(prayerTimings: Timings?) {
        txtDaggerFajr.text =  prayerTimings?.Fajr
        txtDaggerSunrise.text = prayerTimings?.Sunrise
        txtDaggerDhuhur.text = prayerTimings?.Dhuhr
        txtDaggerSunset.text =  prayerTimings?.Sunset
        txtDaggerMidnight.text = prayerTimings?.Midnight
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
        txtDaggerUserTime.text = "user time: " + df.format(currentTime)
        val partOfDay=myViewModel.getPartsofDayByHour(df.format(currentTime).dropLast(3))

        when(partOfDay){
            "night"-> imgDaggerTime.setBackgroundResource(R.drawable.night)
            "sunset"-> imgDaggerTime.setBackgroundResource(R.drawable.sunset)
            "noon"->imgDaggerTime.setBackgroundResource(R.drawable.noon)
            else -> imgDaggerTime.setBackgroundResource(R.drawable.morning)
        }

    }
}
