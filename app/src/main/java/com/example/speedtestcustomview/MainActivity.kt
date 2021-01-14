package com.example.speedtestcustomview

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var valueAnimator: ValueAnimator? = null
    var end = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count = 0
        var count2 = 0
        MainScope().launch {
            while (count < 100) {
                delay(100)

//                if (count / 10 == 0) {
//                    line_demo.progress = 0f
//                } else {

                    line_demo.progress = count.toFloat()
//                }

//                if (count > 30) {
//                    line_demo.layoutParams.height = 1000
//                    line_demo.requestLayout()
//                }
                count++
            }
            while (count2 < 100) {
                delay(100)
                line_demo.progress2 = count2.toFloat()
                count2++
            }
        }
        startAnimation(80,100)

    }

    private fun startAnimation(oldValue : Int, endValue: Int) {
        valueAnimator?.cancel()
        valueAnimator = ValueAnimator().apply {
            setIntValues(oldValue, endValue)
            addUpdateListener { animation ->
                val valueAnimator: Int = animation.animatedValue as Int
                Log.d("abc","$valueAnimator")
            }
            doOnEnd {
                startAnimation(100,50)
                end = true
            }
            if (end){
                interpolator = AccelerateInterpolator()
            }else{
                interpolator =  AccelerateDecelerateInterpolator()
            }
            duration = 2000

//            var cyclerValue = 0f
//            if (endValue > oldValue){
//                cyclerValue = (endValue - oldValue).toFloat()
//            }else{
//                cyclerValue = (oldValue - endValue).toFloat()
//            }
        }
        if (!end)
        valueAnimator?.start()
    }
}