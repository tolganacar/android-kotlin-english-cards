package com.tolganacar.englishcards.ui.adapter

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class StackPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val scaleFactor = 0.85f + (1 - Math.abs(position)) * 0.15f
            val translationFactor = page.width * -position * 0.2f

            if (position < -1) {
                alpha = 0f
            } else if (position <= 1) {
                scaleX = scaleFactor
                scaleY = scaleFactor
                translationX = translationFactor
                alpha = 0.5f + (1 - Math.abs(position)) * 0.5f
            } else {
                alpha = 0f
            }
        }
    }
}
