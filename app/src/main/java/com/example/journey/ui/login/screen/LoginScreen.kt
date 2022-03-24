package com.example.journey.ui.login.screen

import android.os.Bundle
import android.view.View
import com.example.journey.R
import com.example.journey.fragments.navigation.journey.FinishJourneyEvent
import com.example.journey.fragments.navigation.journey.screen.IScreen
import com.example.journey.ui.home.screen.SettingScreenEvent
import com.example.journey.ui.login.LoginEvent

class LoginScreen : IScreen(R.layout.screen_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            offerNavEvent(SettingScreenEvent())
        }

        view.findViewById<View>(R.id.btn_finish_journey).setOnClickListener {
            offerNavEvent(FinishJourneyEvent())
        }
    }
}

class LoginScreenEvent : LoginEvent() {
    override val destination: Int = R.id.loginScreen

    override val action: Int = R.id.action_global_loginScreen
}