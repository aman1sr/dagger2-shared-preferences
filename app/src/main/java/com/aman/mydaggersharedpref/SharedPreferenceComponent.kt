package com.aman.mydaggersharedpref

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferenceModule::class])
interface SharedPreferenceComponent {
    fun inject(mainActivity: MainActivity)
}