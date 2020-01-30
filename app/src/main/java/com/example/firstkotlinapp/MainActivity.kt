package com.example.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IClickListener {
    override fun onClick(name: String) {
        Toast.makeText(this,"Show "+ name+ " Activity" ,Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMenu.setOnClickListener{
            try {
                drawer.openDrawer(GravityCompat.START)

            }
            catch (e:Exception){
               Log.d("myMain", e.message)
            }

            val lstMenuItems = arrayListOf<String>("Contacts", "Calls", "Messages","Setting")
            val  myAdapter = RecyclerAdapter(lstMenuItems,this)
            recyclermenu.adapter=myAdapter
        }
    }
}
