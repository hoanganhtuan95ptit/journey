package com.example.journey.fragments.navigation

import androidx.annotation.LayoutRes
import androidx.lifecycle.viewModelScope
import com.example.journey.fragments.BaseFragment
import com.example.journey.fragments.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import java.io.Serializable

abstract class Navigation(@LayoutRes contentLayoutId: Int = 0) : BaseFragment(contentLayoutId)

open class NavigationViewModel : BaseViewModel() {

    val navigation: ReceiveChannel<NavigationEvent> = Channel(Channel.RENDEZVOUS)

    fun offerNavEvent(event: NavigationEvent) = viewModelScope.launch(Dispatchers.IO) {
        (navigation as Channel).trySend(event)
    }
}

abstract class NavigationEvent : Serializable {

    abstract val action: Int

    var start: Boolean = false
}

interface NavigationParam : Serializable {

}