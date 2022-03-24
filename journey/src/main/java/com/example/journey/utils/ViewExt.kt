package com.example.journey.utils

import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView

fun ViewGroup.findFragmentContainerView(): FragmentContainerView? {

    for (i in 0..childCount) {
        val child = getChildAt(i)

        if (child is FragmentContainerView) {
            return child
        } else if (child is ViewGroup) {
            return child.findFragmentContainerView()
        }
    }

    return null
}