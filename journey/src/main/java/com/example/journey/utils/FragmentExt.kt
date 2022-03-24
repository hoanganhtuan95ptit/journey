package com.example.journey.utils

import androidx.fragment.app.Fragment
import com.example.journey.ui.base.IGuide
import com.example.journey.fragments.navigation.journey.IJourney
import com.example.journey.fragments.navigation.journey.screen.IScreen

fun IJourney<*>.findGuide(): IGuide {
    return requireActivity() as IGuide
}

fun IScreen.findJourney(): IJourney<*> {
    var fragment: Fragment? = this

    while (fragment != null && fragment !is IJourney<*>) {
        fragment = fragment.parentFragment
    }

    return if (fragment is IJourney<*>) {
        fragment
    } else {
        error("Không tìm thấy IJourney")
    }
}
