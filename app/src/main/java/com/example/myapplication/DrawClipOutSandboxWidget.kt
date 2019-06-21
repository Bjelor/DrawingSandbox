package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Region
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log

class DrawClipOutSandboxWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    DrawSandboxWidget(context, attrs, defStyleAttr) {

    var style = 0
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        val canvas = canvas ?: return

        Log.d(tag, "========================================")

        paint.color = ContextCompat.getColor(context, R.color.gray_30)

        canvas.save()

        canvas.clipRect(rect2, Region.Op.DIFFERENCE)

        drawByStyle(canvas, paint, rect)

        canvas.restore()

        paint.color = ContextCompat.getColor(context, R.color.gray_70)

        drawByStyle(canvas, paint, rect2)
    }

    private fun drawByStyle(canvas: Canvas, paint: Paint, rect: Rect) {
        if (style == 0) {
            Log.d(tag, "drawing rect: $rect")
            canvas.drawRect(rect, paint)
        } else {
            Log.d(tag, "drawing oval: $rect")
            rectF.set(rect)
            canvas.drawOval(rectF, paint)
        }
    }
}
