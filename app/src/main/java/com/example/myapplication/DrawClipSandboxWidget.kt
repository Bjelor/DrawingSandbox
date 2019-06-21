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

    /**
     * Just a variable to allow alternating between shapes
     */
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

        // save the current canvas clip to be restored later
        canvas.save()

        // clip canvas to the defined rect
        canvas.clipRect(rect, region)

        // draw rect2 into the canvas => the shape will be clipped by rect
        canvas.drawRect(rect2, paint)

        // restore the canvas to the last saved state => this means that everything that was drawn before the clipping
        // will be shown as well
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
