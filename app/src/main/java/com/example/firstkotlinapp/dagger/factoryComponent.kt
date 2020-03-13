package com.example.firstkotlinapp.dagger

import dagger.Component
import dagger.Provides

@Component(modules = [daggerModule::class])

interface factoryComponent {
    fun provideVMFactory():VMFactoryProvider
}