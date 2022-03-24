package com.example.journey.utils

import android.app.Activity
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

fun Activity.findThisNavController(): NavController {

    val view = findViewById<ViewGroup>(android.R.id.content).findFragmentContainerView()
        ?: error("")

    return Navigation.findNavController(view)
}

fun Fragment.findThisNavController(): NavController {

    var findFragment: Fragment? = this

    while (findFragment != null) {

        if (findFragment is NavHostFragment) {
            return findFragment.navController
        }

        val primaryNavFragment = findFragment.childFragmentManager.primaryNavigationFragment

        if (primaryNavFragment is NavHostFragment) {
            return primaryNavFragment.navController
        }

        findFragment = findFragment.childFragmentManager.fragments[0]
    }

    val view = view
    if (view != null) {
        return Navigation.findNavController(view)
    }

    throw IllegalStateException("Fragment  does not have a NavController set")
}