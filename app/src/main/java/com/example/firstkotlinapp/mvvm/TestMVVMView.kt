package com.example.firstkotlinapp.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.mvp.Timings
import io.reactivex.Observer
import kotlinx.android.synthetic.main.activity_prayer.*
import kotlinx.android.synthetic.main.activity_prayer.imgTime
import kotlinx.android.synthetic.main.activity_prayer.txtUserTime
import kotlinx.android.synthetic.main.activity_test_mvvm.*
import java.text.SimpleDateFormat
import java.util.*

class TestMVVMView : AppCompatActivity() {
    private lateinit var myViewModel:ViewModel
    private lateinit var mainHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_mvvm)
        myViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        //showUserTime()

        btnPrayerTime2.setOnClickListener {
            myViewModel.onPrayerButtonClicked(getCountryCity().first, getCountryCity().second)
        }

        myViewModel.getResponse().observe(this,androidx.lifecycle.Observer {
            showPrayerTime(it.data.timings)
        })

        myViewModel.getError().observe(this,androidx.lifecycle.Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT)
        })

        mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(updateTextTask)

        /*mainHandler.post(object : Runnable {
            override fun run() {
                showUserTime()
                mainHandler.postDelayed(this, 1000)
            }
        })*/

    }

    private val updateTextTask = object : Runnable {
        override fun run() {
            showUserTime()
            mainHandler.postDelayed(this, 1000)
        }
    }

     fun showUserTime() {

         Log.d("myTest","in ShowUserTime Method")

        val currentTime = Calendar.getInstance().time
        val df = SimpleDateFormat("HH:mm")
         Log.d("myTest","user time: " + df.format(currentTime))
        txtUserTime2.text = "user time: " + df.format(currentTime)
        val partOfDay=myViewModel.getPartsofDayByHour(df.format(currentTime).dropLast(3))

        when(partOfDay){
            "night"-> imgTime2.setBackgroundResource(R.drawable.night)
            "sunset"-> imgTime2.setBackgroundResource(R.drawable.sunset)
            "noon"->imgTime2.setBackgroundResource(R.drawable.noon)
            else -> imgTime2.setBackgroundResource(R.drawable.morning)
        }

    }

     fun getCountryCity(): Pair<String, String> {
        val country = edtCountry2.text.trim().toString()
        val city = edtCity2.text.trim().toString()
        return country to city
    }

    fun showPrayerTime(prayerTimings: Timings?) {
        txtFajr2.text =  prayerTimings?.Fajr
        txtSunrise2.text = prayerTimings?.Sunrise
        txtDhuhur2.text = prayerTimings?.Dhuhr
        txtSunset2.text =  prayerTimings?.Sunset
        txtMidnight2.text = prayerTimings?.Midnight
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(updateTextTask)

    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(updateTextTask)
    }

}
