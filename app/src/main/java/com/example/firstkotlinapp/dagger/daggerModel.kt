package com.example.firstkotlinapp.dagger

import androidx.lifecycle.ViewModel
import com.example.firstkotlinapp.mvp.aladhanPojo
import com.example.firstkotlinapp.mvvm.IobservableRetrofit
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class daggerModel @Inject constructor(private val retrofit: Retrofit){
    private fun getPrayTimeData(): IobservableRetrofit {
        return retrofit.create(IobservableRetrofit::class.java)


    }

    //******************************************************************
    fun getAdhanObservable(country: String, city: String): Observable<aladhanPojo> {

        return getPrayTimeData().getTimings(city,country,8)
    }

}
