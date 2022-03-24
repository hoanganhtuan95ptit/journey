package com.example.journey.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.journey.fragments.BaseFragment
import com.example.journey.fragments.navigation.NavigationEvent
import com.example.journey.fragments.navigation.NavigationViewModel
import com.example.journey.fragments.navigation.journey.JourneyEvent
import com.example.journey.utils.findThisNavController
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.koin.getViewModel

abstract class IGuide(@LayoutRes contentLayoutId: Int = 0) : AppCompatActivity(contentLayoutId) {

    val router: GuideViewModel by lazy {
        getKoin().getViewModel(this, GuideViewModel::class)
    }

    private val thisNavController: NavController by lazy {
        findThisNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeNavigation()
    }

    override fun onBackPressed() {

        val list = arrayListOf<BaseFragment>()

        getFragments(supportFragmentManager, list)

        var back = list.isEmpty() || list.all { !it.onBackPressed() }

        if (back) {
            back = !thisNavController.navigateUp()
        }

        if (back) {
            super.onBackPressed()
        }
    }

    private fun getFragments(fragmentManager: FragmentManager, list: ArrayList<BaseFragment>) {

        fragmentManager.fragments.forEach {

            getFragments(it.childFragmentManager, list)

            if (it is BaseFragment) {
                list.add(it)
            }
        }
    }

    private fun observeNavigation() = with(router) {

        lifecycleScope.launchWhenResumed {
            for (event in navigation) onNavigationEvent(event)
        }
    }

    @CallSuper
    protected open fun onNavigationEvent(event: NavigationEvent) {

        if (event !is JourneyEvent) {
            return
        }

        thisNavController.navigate(event.actionToJourney, bundleOf(EVENT to event))
    }

}

class GuideViewModel : NavigationViewModel() {

}

const val EVENT = "event"