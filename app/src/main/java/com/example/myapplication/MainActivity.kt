package com.example.myapplication

import android.graphics.Path
import android.graphics.Region
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<DrawObjectsSandboxWidget>(R.id.sandboxRect)?.apply {
            style = 0
            name = "sandboxRect"
            isVisible = true
        }

        findViewById<DrawObjectsSandboxWidget>(R.id.sandboxOval)?.apply {
            style = 1
            name = "sandboxOval"
            isVisible = true
        }

        findViewById<DrawPathSandboxWidget>(R.id.sandboxPathCWEvenOdd)?.apply {
            name = "sandboxPathCWEvenOdd"
            direction = Path.Direction.CW
            fillType = Path.FillType.EVEN_ODD
            isVisible = true
        }

        findViewById<DrawPathSandboxWidget>(R.id.sandboxPathCCWEvenOdd)?.apply {
            name = "sandboxPathCCWEvenOdd"
            direction = Path.Direction.CW
            fillType = Path.FillType.INVERSE_EVEN_ODD
            isVisible = true
        }

        findViewById<DrawClipSandboxWidget>(R.id.sandboxClip)?.apply {
            name = "sandboxClip"
            style = 0
            region = Region.Op.DIFFERENCE
            isVisible = true
        }

        findViewById<DrawClipSandboxWidget>(R.id.sandboxClip2)?.apply {
            name = "sandboxClip2"
            style = 1
            region = Region.Op.INTERSECT
            isVisible = true
        }

        findViewById<DrawClipOutSandboxWidget>(R.id.sandboxClipOut)?.apply {
            name = "sandboxClipOut"
            style = 0
            isVisible = true
        }

        findViewById<DrawClipOutSandboxWidget>(R.id.sandboxClipOut2)?.apply {
            name = "sandboxClip2"
            style = 1
            isVisible = true
        }
    }

    private var View.isVisible: Boolean
        set(value) {
            visibility = if (value) View.VISIBLE else View.GONE
        }
        get() = visibility == View.VISIBLE

}
