package com.example.firstkotlinapp.koin

import com.example.firstkotlinapp.mvp.aladhanPojo
import com.example.firstkotlinapp.mvvm.IobservableRetrofit
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class koinModel {
    private fun getPrayTimeData(): IobservableRetrofit {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.aladhan.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(IobservableRetrofit::class.java)


    }

    //******************************************************************
    fun getAdhanObservable(country: String, city: String): Observable<aladhanPojo> {

        return getPrayTimeData().getTimings(city,country,8)
    }
}