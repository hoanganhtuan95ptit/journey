package com.example.journey.ui.login.screen

import android.os.Bundle
import android.view.View
import com.example.journey.R
import com.example.journey.fragments.navigation.journey.screen.IScreen
import com.example.journey.ui.login.LoginEvent

class ConfigScreen : IScreen(R.layout.screen_config) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            offerNavEvent(LoginScreenEvent())
        }
    }
}

class ConfigScreenEvent : LoginEvent() {
    override val destination: Int = R.id.configScreen

    override val action: Int = R.id.action_global_configScreen
}