package com.example.journey.ui.home

import com.example.journey.R
import com.example.journey.fragments.navigation.journey.IJourney
import com.example.journey.fragments.navigation.journey.JourneyEvent

class HomeJourney : IJourney<HomeEvent>(R.layout.journey_home)

abstract class HomeEvent : JourneyEvent() {

    override val navigationId: Int = R.navigation.navigation_journey_home

    override val actionToJourney: Int = R.id.action_homeJourney
}