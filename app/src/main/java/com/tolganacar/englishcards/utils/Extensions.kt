package com.tolganacar.englishcards.utils

import android.widget.ImageView

fun ImageView.animateFlip() {
    animate().apply {
        duration = 1000
        rotationYBy(360f)
    }.withEndAction {
        animate().apply {
            duration = 1000
            rotationYBy(3600f)
        }.start()
    }
}