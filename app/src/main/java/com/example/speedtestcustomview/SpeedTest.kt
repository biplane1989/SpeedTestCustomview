package com.example.speedtestcustomview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.collections.ArrayList

class SpeedTest @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


    private val lineWidth = 4f
    private val lineWidthEnd = 1f
    private val pathDownload = Path()
    private val pathUpload = Path()

    var maxSpeedDownload = -1
    var isMaxStatusDownload = false

    var maxSpeedUpload = -1
    var isMaxStatusUpload = false

    var middlePointAbove = PointF()
    var middlePointUnder = PointF()
    var newPoint = PointF(0f, height.toFloat())
    var prevPoint = PointF(0f, height.toFloat())
    val arrSpeedDownload: ArrayList<SpeedData>
    val arrSpeedUpload: ArrayList<SpeedData>

    init {
        arrSpeedDownload = ArrayList()
        arrSpeedUpload = ArrayList()

        pathDownload.moveTo(newPoint.x, newPoint.y)
    }

    val TAG = "123"
    private val linePaintDownload = Paint().apply {

        color = Color.parseColor("#FFA723")
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
        isAntiAlias = true
    }

    private val linePaintDownloadEnd = Paint().apply {
        color = Color.parseColor("#FFA723")
        style = Paint.Style.STROKE
        strokeWidth = lineWidthEnd
        isAntiAlias = true
    }

    private val linePaintUpload = Paint().apply {

        color = Color.parseColor("#5EC7D3")
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
        isAntiAlias = true
    }

    private val linePaintUploadEnd = Paint().apply {
        color = Color.parseColor("#5EC7D3")
        style = Paint.Style.STROKE
        strokeWidth = lineWidthEnd
        isAntiAlias = true
    }


    fun setSpeedDownload(speedData: SpeedData) {
        arrSpeedDownload.add(speedData)
        maxSpeedDownload = setMaxSpeedDownload(speedData.speed)

        if (isMaxStatusDownload) {
            pathDownload.reset()
            newPoint = PointF(0f, height.toFloat())
            prevPoint = PointF(0f, height.toFloat())
            pathDownload.moveTo(newPoint.x, newPoint.y)
            for (item in arrSpeedDownload) {
                setUpDownload(item)
            }
            isMaxStatusDownload = false
        } else {
            setUpDownload(speedData)
        }
    }

    fun setUpDownload(speedData: SpeedData) {
        prevPoint = newPoint
        newPoint = PointF(speedData.percent * width / 100, height - (speedData.speed * height / maxSpeedDownload).toFloat())
        middlePointUnder = PointF((prevPoint.x + newPoint.x) / 2, prevPoint.y)
        middlePointAbove = PointF((prevPoint.x + newPoint.x) / 2, newPoint.y)
        pathDownload.cubicTo(middlePointUnder.x, middlePointUnder.y, middlePointAbove.x, middlePointAbove.y, newPoint.x, newPoint.y)
        invalidate()
    }

    fun setSpeedUpload(speedData: SpeedData) {
        arrSpeedUpload.add(speedData)
        maxSpeedUpload = setMaxSpeedUpdate(speedData.speed)

        if (isMaxStatusUpload) {
            pathUpload.reset()
            newPoint = PointF(0f, height.toFloat())
            prevPoint = PointF(0f, height.toFloat())
            pathUpload.moveTo(newPoint.x, newPoint.y)
            for (item in arrSpeedUpload) {
                setUpUpload(item)
            }
            isMaxStatusUpload = false
        } else {
            setUpUpload(speedData)
        }
    }

    fun setUpUpload(speedData: SpeedData) {
        prevPoint = newPoint
        newPoint = PointF(speedData.percent * width / 100, height - (speedData.speed * height / maxSpeedUpload).toFloat())
        middlePointUnder = PointF((prevPoint.x + newPoint.x) / 2, prevPoint.y)
        middlePointAbove = PointF((prevPoint.x + newPoint.x) / 2, newPoint.y)
        pathUpload.cubicTo(middlePointUnder.x, middlePointUnder.y, middlePointAbove.x, middlePointAbove.y, newPoint.x, newPoint.y)
        invalidate()
    }

    fun setMaxSpeedDownload(speed: Int): Int {
        if (maxSpeedDownload < speed) {
            isMaxStatusDownload = true
            return speed
        }
        return maxSpeedDownload
    }

    fun setMaxSpeedUpdate(speed: Int): Int {
        if (maxSpeedUpload < speed) {
            isMaxStatusUpload = true
            return speed
        }
        return maxSpeedUpload
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(pathDownload, linePaintDownload)
        canvas?.drawPath(pathUpload, linePaintUpload)

        canvas?.drawLine(newPoint.x, newPoint.y, width.toFloat(), newPoint.y, linePaintDownloadEnd)
        canvas?.drawLine(newPoint.x, newPoint.y, width.toFloat(), newPoint.y, linePaintUploadEnd)
    }
}