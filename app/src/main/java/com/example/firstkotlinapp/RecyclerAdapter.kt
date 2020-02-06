package com.example.firstkotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclermenu_item.view.*

class RecyclerAdapter(val names:List<String>, val clickListener:(String)->Unit) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.recyclermenu_item,parent,false)
        return MyViewHolder(v,clickListener)

    }

    override fun getItemCount(): Int = names.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(names[position])
    }

    class MyViewHolder(val myView: View, val clickListener:(String)->Unit) : RecyclerView.ViewHolder(myView) {

        fun onBind(name:String){
            myView.txtName.text = name
            myView.setOnClickListener{
                clickListener(name)
            }
        }

    }

}