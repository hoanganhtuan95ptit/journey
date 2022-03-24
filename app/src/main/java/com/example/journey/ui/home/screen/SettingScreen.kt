package com.example.journey.ui.home.screen

import android.os.Bundle
import android.view.View
import com.example.journey.R
import com.example.journey.fragments.navigation.journey.screen.IScreen
import com.example.journey.ui.home.HomeEvent

class SettingScreen : IScreen(R.layout.screen_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnClickListener {
            offerNavEvent(HomeScreenEvent())
        }
    }
}

class SettingScreenEvent : HomeEvent() {
    override val destination: Int = R.id.settingScreen

    override val action: Int = R.id.action_global_settingScreen
}