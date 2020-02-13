package com.example.firstkotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.firstkotlinapp.mvp.PrayerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!this.isInternetAvailable()){
            this.showToast("No Internet access",Toast.LENGTH_LONG)
        }

        btnMenu.setOnClickListener{
            try {
                drawer.openDrawer(GravityCompat.START)

            }
            catch (e:Exception){
               Log.d("myMain", e.message)
            }

            var clickListener: (String)->Unit={
                 //Toast.makeText(this,"Show "+ it+ " Activity" ,Toast.LENGTH_LONG).show()
                //this.showToast("Show "+ it+ " Activity")
                val intent=Intent(this, PrayerActivity::class.java)
                startActivity(intent)

        }

            val lstMenuItems = arrayListOf<String>("Prayer Times")
            val  myAdapter = RecyclerAdapter(lstMenuItems,clickListener)
            recyclermenu.adapter=myAdapter


        }


    }
}
