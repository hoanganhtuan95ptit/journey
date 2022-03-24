package com.example.journey.fragments.navigation.journey.screen

import androidx.annotation.LayoutRes
import com.example.journey.fragments.navigation.journey.JourneyViewModel
import com.example.journey.fragments.navigation.Navigation
import com.example.journey.fragments.navigation.NavigationEvent
import com.example.journey.utils.findJourney

abstract class IScreen(@LayoutRes contentLayoutId: Int = 0) : Navigation(contentLayoutId) {

    private val router: JourneyViewModel by lazy {
        findJourney().router
    }

    fun offerNavEvent(event: NavigationEvent) {
        router.offerNavEvent(event)
    }

}

class FinishScreenEvent : NavigationEvent() {
    override val action: Int = 0
}