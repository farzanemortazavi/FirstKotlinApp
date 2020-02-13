package com.example.firstkotlinapp.mvp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class prayerModel(private val presenter: PrayerContract.Presenter) {

    fun getPrayTimeData(country: String, city: String){
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.aladhan.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val adhanInterface= retrofit.create(Iretrofit::class.java)

        adhanInterface.getTimings(city,country,8)
            .enqueue(object: Callback<aladhanPojo>{
                override fun onFailure(call: Call<aladhanPojo>, t: Throwable) {
                    //Log.d("myerror",t.message)
                    presenter.onRetrofitResponse(false,null)
                }

                override fun onResponse(call: Call<aladhanPojo>, response: Response<aladhanPojo>) {
                  val result= response.body()?.data?.timings
                    presenter.onRetrofitResponse(true,result)
                }

            })
    }

}