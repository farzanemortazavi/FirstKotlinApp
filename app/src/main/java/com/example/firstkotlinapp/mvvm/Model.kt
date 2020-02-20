package com.example.firstkotlinapp.mvvm

import com.example.firstkotlinapp.mvp.Iretrofit
import com.example.firstkotlinapp.mvp.aladhanPojo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Model {


    private fun getPrayTimeData():IobservableRetrofit{
        val retrofit= Retrofit.Builder()
            .baseUrl("https://api.aladhan.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(IobservableRetrofit::class.java)


    }

    ///////////
    fun getAdhanObservable(country: String, city: String):Observable<aladhanPojo>{

        return getPrayTimeData().getTimings(city,country,8)
    }
}