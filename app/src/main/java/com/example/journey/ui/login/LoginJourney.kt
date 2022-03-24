package com.example.journey.ui.login

import com.example.journey.R
import com.example.journey.fragments.navigation.journey.IJourney
import com.example.journey.fragments.navigation.journey.JourneyEvent

class LoginJourney : IJourney<LoginEvent>(R.layout.journey_login)

abstract class LoginEvent : JourneyEvent() {

    override val navigationId: Int = R.navigation.navigation_journey_login

    override val actionToJourney: Int = R.id.action_loginJourney

}