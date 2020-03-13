package com.example.firstkotlinapp.koin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstkotlinapp.mvp.aladhanPojo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class koinViewModel: ViewModel() {

    private val disposable= CompositeDisposable()
    private val model= koinModel()
    private val adhanResponse= MutableLiveData<aladhanPojo>()
    private val adhanError= MutableLiveData<String?>()

    fun getPartsofDayByHour(time: String): String {
        val hour=time.toInt()
        var result=when(hour){
            in 5..11 -> {"morning"}
            in 12..16-> {"noon"}
            in 17..19 ->{"sunset"}
            else ->{"night"}
        }
        return result
    }
    //*****************************************************
    fun onPrayerButtonClicked(country:String,city:String) {
        //val (country, city)=view.getCountryCity()

        disposable.add(model.getAdhanObservable(country,city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adhanResponse.value=it
                Log.d("myTest",it.data.timings.Fajr)

            },{
                Log.d("myTest",it.message)
                adhanError.value="Error in Retrieving aladhan data"
            }))


    }
    //*******************************************************
    fun getResponse(): LiveData<aladhanPojo> = adhanResponse
    fun getError(): LiveData<String?> = adhanError



    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}