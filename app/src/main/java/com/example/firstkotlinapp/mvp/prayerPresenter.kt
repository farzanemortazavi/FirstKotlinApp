package com.example.firstkotlinapp.mvp

import org.jetbrains.annotations.Contract

class prayerPresenter(private val view:PrayerContract.View):PrayerContract.Presenter {
    override fun getPartsofDayByHour(time: String): String {
        val hour=time.toInt()
        var result=when(hour){
            in 5..11 -> {"morning"}
            in 12..16-> {"noon"}
            in 17..19 ->{"sunset"}
            else ->{"night"}
        }
        return result
    }

    override fun onRetrofitResponse(isSucceed: Boolean, parayerTiming: Timings?) {
        if(isSucceed)
            view.showPrayerTime(parayerTiming)
        else
            view.showError()
    }

    val model=prayerModel(this)

    override fun onPrayerButtonClicked() {
        val (country, city)=view.getCountryCity()
        model.getPrayTimeData(city, country)

    }
}