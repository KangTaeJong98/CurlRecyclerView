package com.example.curlclone

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

class CurlFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var curlFactor = 0F

    private val curlStrokePaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
        color = Color.BLACK
    }
    private val curlFillPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val bottomFold = PointF()
    private val topFold = PointF()
    private val bottomFoldTip = PointF()
    private val topFoldTip = PointF()
    private val clipPath = Path()
    private val curlPath = Path()

    fun setCurlFactor(factor: Float) {
        updateBottomFold(factor)
        updateTopFold()
        updateBottomFoldTip()
        updateTopFoldTip()
        updateClipPath(factor)
        updateCurlPath()

        curlFactor = factor
        invalidate()
    }

    private fun updateBottomFold(factor: Float) {
        val curl = if (factor < 0) {
            factor + 1F
        } else {
            factor
        }

        bottomFold.x = width * curl
        bottomFold.y = height.toFloat()
    }

    private fun updateTopFold() {
        if (bottomFold.x > width / 2F) {
            topFold.x = width.toFloat()
            topFold.y = height - (width - bottomFold.x) * height / bottomFold.x
        } else {
            topFold.x = 2F * bottomFold.x
            topFold.y = 0F
        }
    }

    private fun updateBottomFoldTip() {
        val angle = atan((height - topFold.y) / (topFold.x - bottomFold.x))
        val cosFactor = cos(2 * angle)
        val sinFactor = sin(2 * angle)
        val foldWidth = width - bottomFold.x

        bottomFoldTip.x = (bottomFold.x + foldWidth * cosFactor)
        bottomFoldTip.y = (height - foldWidth * sinFactor)
    }

    private fun updateTopFoldTip() {
        val angle = atan((height - topFold.y) / (topFold.x - bottomFold.x))
        val cosFactor = cos(2 * angle)
        val sinFactor = sin(2 * angle)
        val foldWith = width - topFold.x

        if (bottomFold.x > width / 2) {
            topFoldTip.x = topFold.x
            topFoldTip.y = topFold.y
        } else {
            topFoldTip.x = (topFold.x + foldWith * cosFactor)
            topFoldTip.y = -(sinFactor * foldWith)
        }
    }

    private fun updateClipPath(factor: Float) {
        clipPath.reset()
        if (factor < 0F) {
            clipPath.moveTo(0F, 0F)
            if (topFold.y != 0F) {
                clipPath.lineTo(width.toFloat(), 0F)
            }

            clipPath.lineTo(topFold.x, topFold.y)
            clipPath.lineTo(bottomFold.x, bottomFold.y)
            clipPath.lineTo(0F, height.toFloat())
        } else {
            clipPath.moveTo(width.toFloat(), height.toFloat())
            if (topFold.y == 0F) {
                clipPath.lineTo(width.toFloat(), 0F)
            }

            clipPath.lineTo(topFold.x, topFold.y)
            clipPath.lineTo(bottomFold.x, bottomFold.y)
        }
        clipPath.close()
    }

    private fun updateCurlPath() {
        curlPath.reset()
        curlPath.moveTo(bottomFold.x, bottomFold.y)
        curlPath.lineTo(bottomFoldTip.x, bottomFoldTip.y)
        curlPath.lineTo(topFoldTip.x, topFoldTip.y)
        curlPath.lineTo(topFold.x, topFold.y)
        curlPath.close()
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.save()
        if (curlFactor != 0F && curlFactor != 1F && curlFactor != -1F) {
            canvas.clipPath(clipPath)
        }

        super.dispatchDraw(canvas)
        canvas.restore()

        canvas.drawPath(curlPath, curlFillPaint)
    }
}