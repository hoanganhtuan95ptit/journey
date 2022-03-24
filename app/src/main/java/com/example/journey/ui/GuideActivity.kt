package com.example.journey.ui

import android.os.Bundle
import com.example.journey.R
import com.example.journey.ui.base.IGuide

class GuideActivity : IGuide() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}