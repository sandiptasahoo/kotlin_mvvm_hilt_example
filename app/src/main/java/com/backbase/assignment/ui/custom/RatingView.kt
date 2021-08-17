package com.backbase.assignment.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.backbase.assignment.R

class RatingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    private var progress = 0.0f
    private val currentStrokeSize = 8f
    private val padding = currentStrokeSize / 2
    private val rect = RectF()

    fun setProgress(percent: Float) {
        progress = percent
        invalidate()
    }

    private fun bgPaint(progress: Float) = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = currentStrokeSize
        strokeCap = Paint.Cap.ROUND
        color = when{
            progress < 0.5f -> context.getColor(R.color.yellow_bg_rating_color)
            else -> context.getColor(R.color.green_bg_rating_color)
        }
        textAlign = Paint.Align.CENTER
    }
    private fun fillPaint(progress: Float) = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = currentStrokeSize
        strokeCap = Paint.Cap.ROUND
        color = when{
            progress < 0.5f -> context.getColor(R.color.yellow_fill_rating_color)
            else -> context.getColor(R.color.green_fill_rating_color)
        }
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.apply {
            left = padding
            top = padding
            right = width.toFloat() - padding
            bottom = height.toFloat() - padding
        }
//        Full Circle
        canvas.drawArc(rect, 0f, 360f, false, bgPaint(progress))
//        Active Circle
        canvas.drawArc(rect, -90f, 360f * progress, false, fillPaint(progress))
    }
}