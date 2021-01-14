package com.example.speedtestcustomview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*
import java.util.jar.Attributes

class SpeedTest @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


    private val lineWidth = 4f
    private val lineWidth1 = 2f
    private val lineRectf = RectF()
    private val path = Path()
    private val path2 = Path()

    val random = Random(100)
    var newY = 0f
    var newY2 = 0f

    init {
        path.moveTo(100f, 100f)
        path2.moveTo(100f, 100f)
    }

    val TAG = "123"
    private val linePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
        isAntiAlias = true
    }

    private val linePaint1 = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = lineWidth1
        isAntiAlias = true
    }

    private val linePaint2 = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = lineWidth
        isAntiAlias = true
    }
    private val linePaint3 = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = lineWidth1
        isAntiAlias = true
    }

    var progress: Float = 0f
        set(value) {
            field = value
            Log.d(TAG, "x1 : $width/100")
            newY = 100f + random.nextFloat() * height /2
            path.lineTo(100f + progress * width / 100, newY)
            invalidate()
        }
    var progress2: Float = 0f
        set(value) {
            field = value
            newY2 = 100f + random.nextFloat() * height / 25
            path2.lineTo(100f + progress2 * width / 100, newY2)
            invalidate()
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {  // khi view thay doi size thi se goi vao ham nay
        super.onSizeChanged(w, h, oldw, oldh)

        Log.d(TAG, "onSizeChanged: w: $h oldHeight : $oldh")

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, linePaint)
        canvas?.drawPath(path2, linePaint2)

        canvas?.drawLine(100f + progress * width / 100, newY, width.toFloat(), newY, linePaint1)
        canvas?.drawLine(100f + progress2 * width / 100, newY2, width.toFloat(), newY2, linePaint3)

    }
}