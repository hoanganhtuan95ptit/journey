package com.example.journey.fragments.navigation.journey

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.journey.ui.base.EVENT
import com.example.journey.ui.base.GuideViewModel
import com.example.journey.fragments.navigation.Navigation
import com.example.journey.fragments.navigation.NavigationEvent
import com.example.journey.fragments.navigation.NavigationViewModel
import com.example.journey.fragments.navigation.journey.screen.FinishScreenEvent
import com.example.journey.utils.allGenericClass
import com.example.journey.utils.allSuperClass
import com.example.journey.utils.findGuide
import com.example.journey.utils.findThisNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.koin.getViewModel
import kotlin.coroutines.coroutineContext


abstract class IJourney<E : JourneyEvent>(@LayoutRes contentLayoutId: Int = 0) :
    Navigation(contentLayoutId) {

    val router: JourneyViewModel by lazy {
        getKoin().getViewModel(this, JourneyViewModel::class)
    }

    private val parentRouter: GuideViewModel by lazy {
        findGuide().router
    }

    private val journeyEventClass: ArrayList<Class<*>> by lazy {
        allGenericClass()
    }

    private val thisNavController: NavController by lazy {
        findThisNavController()
    }

    private val parentNavController: NavController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNavigation()
    }

    override fun onBackPressed(): Boolean {

        return if (!thisNavController.navigateUp()) {
            super.onBackPressed()
        } else {
            true
        }
    }

    private fun observeNavigation() = with(router) {

        viewLifecycleOwner.lifecycleScope.launch {

            val startEvent = (arguments?.getSerializable(EVENT) as? NavigationEvent)?.apply {
                arguments?.remove(EVENT)
                start = true
            }

            if (startEvent != null) {
                offerNavEvent(startEvent)
            }

            for (event in navigation) {
                onNavigationEvent(event)
            }
        }
    }

    @CallSuper
    protected open suspend fun onNavigationEvent(event: NavigationEvent) {

        if (event is FinishScreenEvent) {
            requireActivity().onBackPressed()
        }

        if (event is FinishJourneyEvent && !parentNavController.navigateUp()) {
            requireActivity().onBackPressed()
        }

        if (event !is JourneyEvent) {
            return
        }

        val navigationCurrent = withContext(coroutineContext + Dispatchers.IO) {
            val allSuperClass = event.allSuperClass()

            journeyEventClass.any { allSuperClass.contains(it) }
        }

        if (navigationCurrent && event.start) {

            val graph = thisNavController.graph
            graph.startDestination = event.destination

            thisNavController.graph = graph
        } else if (navigationCurrent) {

            thisNavController.navigate(event.action)
        } else {

            parentRouter.offerNavEvent(event)
        }
    }

    fun offerNavEvent(event: NavigationEvent) {
        parentRouter.offerNavEvent(event)
    }

}

class JourneyViewModel : NavigationViewModel() {

}

class FinishJourneyEvent : NavigationEvent() {

    override val action: Int = 0
}

abstract class JourneyEvent : NavigationEvent() {

    abstract val destination: Int

    abstract val navigationId: Int

    abstract val actionToJourney: Int
}