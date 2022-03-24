package com.example.journey

import android.app.Application
import com.example.journey.ui.base.GuideViewModel
import com.example.journey.fragments.navigation.journey.JourneyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    private val viewModel = module(override = true) {
        viewModel {
            GuideViewModel()
        }
        viewModel {
            JourneyViewModel()
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(
                viewModel
            )
        }
    }
}