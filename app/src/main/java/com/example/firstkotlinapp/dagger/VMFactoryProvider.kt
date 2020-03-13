package com.example.firstkotlinapp.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class VMFactoryProvider @Inject constructor (val model:daggerModel):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(daggerViewModel::class.java) -> return daggerViewModel(model) as T
            else -> throw IllegalArgumentException("viewModel is not provided")
        }

    }
}