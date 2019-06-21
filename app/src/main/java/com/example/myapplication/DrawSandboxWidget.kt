package com.example.myapplication

import android.content.*
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.*
import android.view.View
import kotlin.math.*

open class DrawSandboxWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    var name: String = id.toString()
    protected val tag: String
        get() = javaClass.simpleName + " " + name

    protected var rect: Rect = Rect()
    protected var rectF: RectF = RectF()

    protected var rect2: Rect = Rect()
    protected var rectF2: RectF = RectF()

    protected val desiredRectWidth = 200

    protected var rectPadding = 25
    protected var rectSize = 0
    protected var rectHeight = 0

    protected var textSize = 10f

    protected var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
    }

    init {
        visibility = View.GONE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        rectSize = desiredRectWidth
        rectHeight = (rectSize * 1.25).roundToInt()

        val desiredWidth = (rectSize * 1.5 + 2 * rectPadding).roundToInt()
        val desiredHeight = (rectHeight * 1.5 + 2 * rectPadding + textSize + rectPadding).roundToInt()

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> Math.min(desiredWidth, widthSize)
            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        rect.apply {
            left = rectPadding
            top = rectPadding
            right = rectPadding + rectSize
            bottom = rectPadding + rectHeight
        }
        rectF = RectF(rect)

        rect2.apply {
            left = rectPadding + (rectSize / 2)
            top = rectPadding + (rectHeight / 2)
            right = rectPadding + (rectSize * 1.5).roundToInt()
            bottom = rectPadding + (rectHeight * 1.5).roundToInt()
        }
        rectF2 = RectF(rect2)
    }
}
