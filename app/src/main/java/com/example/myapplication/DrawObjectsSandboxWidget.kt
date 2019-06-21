package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log

class DrawObjectsSandboxWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    DrawSandboxWidget(context, attrs, defStyleAttr) {

    private var drawBounds = Rect(0,0, 100, 100)

    var style = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        val canvas = canvas ?: return

        Log.d(tag, "========================================")

//        canvas.clipRect(drawBounds)

        paint.color = ContextCompat.getColor(context, R.color.gray_30)

        drawByStyle(canvas, paint, rect)

        paint.color = ContextCompat.getColor(context, R.color.gray_70)

        drawByStyle(canvas, paint, rect2)
    }

    private fun drawByStyle(canvas: Canvas, paint: Paint, rect: Rect) {
        rectF.set(rect)
        invalidate(rect)
        // check if defined object lies inside the canvas
//        if (canvas.quickReject(rectF, Canvas.EdgeType.BW)) return

        if (style == 0) {
            Log.d(tag, "drawing rect: $rect")
            canvas.drawRect(rect, paint)
        } else {
            Log.d(tag, "drawing oval: $rect")
            canvas.drawOval(rectF, paint)
        }
    }
}
