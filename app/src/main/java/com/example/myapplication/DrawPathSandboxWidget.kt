package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.Log

class DrawPathSandboxWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    DrawSandboxWidget(context, attrs, defStyleAttr) {

    var fillType = Path.FillType.INVERSE_EVEN_ODD
    var direction = Path.Direction.CW

    private var path = Path()

    override fun onDraw(canvas: Canvas?) {
        val canvas = canvas ?: return

        Log.d(tag, "========================================")

        rectF.set(rect)
        rectF2.set(rect2)

        paint.color = ContextCompat.getColor(context, R.color.gray_30)

        path.fillType = fillType

        path.addRect(rectF, direction)
        path.addOval(rectF2, direction)

        Log.d(tag, "drawing path: $path")

        // path will be drawn in a single pass => no overdraw but overlapping shapes will probably cause a mess if left
        // unattended
        canvas.drawPath(path, paint)
    }

}