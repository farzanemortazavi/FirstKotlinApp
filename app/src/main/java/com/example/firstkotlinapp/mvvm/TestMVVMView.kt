package com.example.firstkotlinapp.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.mvp.Timings
import kotlinx.android.synthetic.main.activity_prayer.*
import kotlinx.android.synthetic.main.activity_prayer.imgTime
import kotlinx.android.synthetic.main.activity_prayer.txtUserTime
import kotlinx.android.synthetic.main.activity_test_mvvm.*
import java.text.SimpleDateFormat
import java.util.*

class TestMVVMView : AppCompatActivity() {
    val myViewModel=ViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_mvvm)
        showUserTime()

        btnPrayerTime.setOnClickListener {
            myViewModel.onPrayerButtonClicked(getCountryCity().first,getCountryCity().second)
        }
    }

     fun showUserTime() {

        val currentTime = Calendar.getInstance().time
        val df = SimpleDateFormat("HH:mm")
        txtUserTime.text = "user time: " + df.format(currentTime)
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

}
