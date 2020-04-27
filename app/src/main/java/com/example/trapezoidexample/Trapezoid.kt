package com.example.trapezoidexample

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class Trapezoid @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val shapePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = 8f
    }

    val pathForCurve = Path()
    val cot45degree = 1

    /**
     * Sides of trapezoid should be equal for our case
     * our trapezoid will take the whole width. We also need side line to be at the angle of 45 degree
     * To find coordinates for the top line of trapezoid we will use the formula
     * b = a-h*(ctg(α) + ctg(β)) to find the length of top side of trapezoid
     * then we need to subtract b from a and divide that by 2 to find x
     * 0+x will give us x-coordinate of the begining of the top line
     * width-x will give us x-coordinate for the end of top line
     *
     *
     *      __________b______
     *     /|                | \
     *    / |                |  \
     *   /  |                |   \
     *  / α |              h |  β \
     * /)___|________________|____(\
     *   x         a            x
     */
    override fun onDraw(canvas: Canvas?) {

        val bottomEdge = height.toFloat()
        val endEdge = width.toFloat()

        val lengthOfTop = endEdge - bottomEdge * (cot45degree + cot45degree)
        val topLineOffset = (endEdge - lengthOfTop)/2
        val rightCurveStart = endEdge - topLineOffset
        val bottomLeftCurveX = topLineOffset/2
        val topRightCurveX = rightCurveStart + (topLineOffset/2)

        pathForCurve.moveTo(0f, bottomEdge)
        pathForCurve.cubicTo(bottomLeftCurveX, bottomEdge, bottomLeftCurveX, 0f, topLineOffset, 0f)
        pathForCurve.lineTo(rightCurveStart, 0f)
        pathForCurve.cubicTo(topRightCurveX, 0f, topRightCurveX, bottomEdge, endEdge, bottomEdge)
        canvas?.drawPath(pathForCurve, shapePaint)
    }
}