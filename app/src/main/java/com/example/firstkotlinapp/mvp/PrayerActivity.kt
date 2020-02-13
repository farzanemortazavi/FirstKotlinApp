package com.example.firstkotlinapp.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firstkotlinapp.R
import com.example.firstkotlinapp.showToast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_prayer.*
import java.text.SimpleDateFormat
import java.util.*

class PrayerActivity : AppCompatActivity(), PrayerContract.View {
    private val presenter = prayerPresenter(this)

    override fun showError() {
        this.showToast("Error in retrieving data")
    }

    override fun showUserTime() {

        val currentTime = Calendar.getInstance().time
        val df = SimpleDateFormat("HH:mm")
        txtUserTime.text = "user time: " + df.format(currentTime)
        val partOfDay=presenter.getPartsofDayByHour(df.format(currentTime).dropLast(3))

        when(partOfDay){
            "night"-> imgTime.setBackgroundResource(R.drawable.night)
            "sunset"-> imgTime.setBackgroundResource(R.drawable.sunset)
            "noon"->imgTime.setBackgroundResource(R.drawable.noon)
            else -> imgTime.setBackgroundResource(R.drawable.morning)
        }
        //imgTime.setBackgroundResource(R.drawable.night)


    }

    override fun getContryCity(): Pair<String, String> {
        val country = edtCountry.text.trim().toString()
        val city = edtCity.text.trim().toString()
        return country to city
    }

    override fun showPrayerTime(prayerTimings: Timings?) {
        txtFajr.text =  prayerTimings?.Fajr
        txtSunrise.text = prayerTimings?.Sunrise
        txtDhuhur.text = prayerTimings?.Dhuhr
        txtSunset.text =  prayerTimings?.Sunset
        txtMidnight.text = prayerTimings?.Midnight
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prayer)

        showUserTime()

        btnPrayerTime.setOnClickListener {
            presenter.onPrayerButtonClicked()
        }

        // Log.d("myTest", "12:53".split(":"))
        //val s = "12:44".dropLast(3)
        //Log.d("myTest", s)
    }
}
