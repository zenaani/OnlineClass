package com.zen.onlineclass

import android.view.View
import android.view.animation.AnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

fun View.slideUp(animTime: Long, startOffset: Long) {
    val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up).apply{
        duration = animTime
        interpolator = FastOutSlowInInterpolator()
        this.startOffset = startOffset
    }

    startAnimation(slideUp)

}