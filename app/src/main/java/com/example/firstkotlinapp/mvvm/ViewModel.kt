package com.example.firstkotlinapp.mvvm

import com.example.firstkotlinapp.mvp.Timings
import com.example.firstkotlinapp.mvp.prayerModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewModel {
    val disposable=CompositeDisposable()
    val model= Model()

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


     fun onPrayerButtonClicked(country:String,city:String) {
        //val (country, city)=view.getCountryCity()

         disposable.add(model.getAdhanObservable(country,city)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({
                 val result=it.data.timings
                 //send result to view

             },{
                 val error=it.message
                 //send error to view
             }))


    }

    fun clearDisposable(){
        disposable.dispose()
    }
}