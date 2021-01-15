package com.example.speedtestcustomview

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainScope().launch {
            for (i in 1 until 100) {
                Log.d("TAG", "onCreate: random ${random.nextFloat()}")
                if (i < 50) {
                    delay(100)

                } else {
                    delay(40)
                }

                val speedData = SpeedData()

                speedData.speed = 1
                if (i > 10) {
                    speedData.speed = random.nextInt(2 - 1 + 1) + 1
//                    speedData.speed = 2
                }
                if (i > 20) {
//                    speedData.speed = random.nextInt(3 - 2 + 1) + 2
                    speedData.speed = 3
                }
                if (i > 30) {
                    speedData.speed = random.nextInt(4 - 3 + 1) + 3
//                    speedData.speed = 4
                }
                if (i > 40) {
//                    speedData.speed = random.nextInt(5 - 4 + 1) + 4
                    speedData.speed = 5
                }
                if (i > 50) {
//                    speedData.speed = random.nextInt(6 - 5 + 1) + 5
                    speedData.speed = 6
                }
                if (i > 60) {
                    speedData.speed = random.nextInt(7 - 6 + 1) + 6
//                    speedData.speed = 7
                }
                if (i > 70) {
                    speedData.speed = random.nextInt(8 - 7 + 1) + 7
//                    speedData.speed = 8
                }
                if (i > 80) {
//                    speedData.speed = random.nextInt(9 - 8 + 1) + 8
                    speedData.speed = 9
                }
                if (i > 90) {
                    speedData.speed = random.nextInt(10 - 9 + 1) + 9
//                    speedData.speed = 10
                }

                speedData.percent = i.toFloat()
                line_demo.setSpeedDownload(speedData)
                Log.d("TAG", "onCreate: speedData : ${speedData}")
            }

            for (i in 1 until 100) {
                Log.d("TAG", "onCreate: random ${random.nextFloat()}")
                delay(100)

                val speedData = SpeedData()

                speedData.speed = 1
                if (i > 10) {
                    speedData.speed = random.nextInt(2 - 1 + 1) + 1
//                    speedData.speed = 2
                }
                if (i > 20) {
                    speedData.speed = random.nextInt(3 - 2 + 1) + 2
//                    speedData.speed = 3
                }
                if (i > 30) {
//                    speedData.speed = random.nextInt(4 - 3 + 1) + 3
                    speedData.speed = 4
                }
                if (i > 40) {
                    speedData.speed = random.nextInt(5 - 4 + 1) + 4
//                    speedData.speed = 5
                }
                if (i > 50) {
//                    speedData.speed = random.nextInt(6 - 5 + 1) + 5
                    speedData.speed = 6
                }
                if (i > 60) {
//                    speedData.speed = random.nextInt(7 - 6 + 1) + 6
                    speedData.speed = 7
                }
                if (i > 70) {
                    speedData.speed = random.nextInt(8 - 7 + 1) + 7
//                    speedData.speed = 8
                }
                if (i > 80) {
                    speedData.speed = random.nextInt(9 - 8 + 1) + 8
//                    speedData.speed = 9
                }
                if (i > 90) {
                    speedData.speed = random.nextInt(10 - 9 + 1) + 9
//                    speedData.speed = 10
                }

                speedData.percent = i.toFloat()
                line_demo.setSpeedUpload(speedData)
                Log.d("TAG", "onCreate: speedData : ${speedData}")
            }
        }
    }

}