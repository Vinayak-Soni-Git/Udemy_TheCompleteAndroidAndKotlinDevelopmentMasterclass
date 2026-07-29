package com.example.udemy_thecompleteandroidandkotlindevelopmentmasterclass.DrawingApp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.createBitmap
import androidx.core.graphics.toColorInt

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private lateinit var drawPath: FingerPath
    private lateinit var canvasPaint: Paint
    private lateinit var drawPaint: Paint
    private var color = Color.BLACK
    private lateinit var canvas: Canvas
    private lateinit var canvasBitmap: Bitmap
    private var brushSize: Float = 0.toFloat()
    private val paths = mutableListOf<FingerPath>()

    init {
        setupDrawing()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = createBitmap(w, h)
        canvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap, 0f, 0f, drawPaint)

        for (path in paths) {
            drawPaint.strokeWidth = path.brushThickness
            drawPaint.color = path.color
            canvas.drawPath(path, drawPaint)
        }

        if (!drawPath.isEmpty) {
            drawPaint.strokeWidth = drawPath.brushThickness
            drawPaint.color = drawPath.color
            canvas.drawPath(drawPath, drawPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath.color = color
                drawPath.brushThickness = brushSize
                drawPath.reset()
                drawPath.moveTo(touchX!!, touchY!!)
            }

            MotionEvent.ACTION_MOVE -> {
                drawPath.lineTo(touchY!!, touchX!!)
            }

            MotionEvent.ACTION_UP -> {
                drawPath = FingerPath(color, brushSize)
                paths.add(drawPath)
            }

            else -> return false
        }
        invalidate()
        return true
    }

    private fun setupDrawing() {
        drawPaint = Paint()
        drawPath = FingerPath(color, brushSize)
        drawPaint.color = color
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeCap = Paint.Cap.ROUND

        canvasPaint = Paint(Paint.DITHER_FLAG)
        brushSize = 20.toFloat()
    }

    fun changeBrushSize(newSize: Float) {
        brushSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            newSize, resources.displayMetrics
        )
    }

    fun setColor(newColor: Any) {
        if (newColor is String) {
            color = newColor.toColorInt()
            drawPaint.color = color
        } else {
            drawPaint.color = newColor as Int
        }
    }

    fun undoPath() {
        if (paths.isNotEmpty()) {
            paths.removeAt(paths.size - 1)
            invalidate()
        }
    }

    internal inner class FingerPath(var color: Int, var brushThickness: Float) : Path() {

    }
}