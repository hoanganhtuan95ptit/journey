package com.example.journey.ui.home.screen

import android.os.Bundle
import android.view.View
import com.example.journey.R
import com.example.journey.fragments.navigation.journey.screen.IScreen
import com.example.journey.ui.home.HomeEvent
import com.example.journey.ui.login.screen.ConfigScreenEvent

class HomeScreen : IScreen(R.layout.screen_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            offerNavEvent(ConfigScreenEvent())
        }
    }
}

class HomeScreenEvent : HomeEvent() {
    override val destination: Int = R.id.homeScreen

    override val action: Int = R.id.action_global_homeScreen
}