package com.example.speedtestcustomview

data class SpeedData(var speed: Int = 0, var percent: Float = 0f) {
    override fun toString(): String {
        return "speed: $speed  - percent: $percent"
    }
}
