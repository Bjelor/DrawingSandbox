package com.example.myapplication

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log

class DrawClipSandboxWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    DrawSandboxWidget(context, attrs, defStyleAttr) {

    var style = 0
        set(value) {
            field = value
            invalidate()
        }

    var region = Region.Op.INTERSECT

    override fun onDraw(canvas: Canvas?) {
        val canvas = canvas ?: return

        Log.d(tag, "========================================")

        paint.color = ContextCompat.getColor(context, R.color.gray_30)

        drawByStyle(canvas, paint, rect)

        paint.color = ContextCompat.getColor(context, R.color.gray_70)

        drawByStyle(canvas, paint, rect2)

        paint.color = Color.RED

        canvas.save()
        canvas.clipRect(rect, region)

        canvas.drawRect(rect2, paint)

        canvas.restore()
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
