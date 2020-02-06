package com.example.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
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
               // Toast.makeText(this,"Show "+ it+ " Activity" ,Toast.LENGTH_LONG).show()
                this.showToast("Show "+ it+ " Activity")

        }

            val lstMenuItems = arrayListOf<String>("Contacts", "Calls", "Messages","Setting")
            val  myAdapter = RecyclerAdapter(lstMenuItems,clickListener)
            recyclermenu.adapter=myAdapter


        }


    }
}
