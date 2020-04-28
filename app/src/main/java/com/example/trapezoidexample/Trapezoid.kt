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
    val cot30degree = 1.7f

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

        val trapezoidHeight = height.toFloat()
        val trapezoidWidth = width.toFloat()

        val lengthOfTop = trapezoidWidth - trapezoidHeight * (cot30degree + cot30degree)
        val topLineOffsetStart = (trapezoidWidth - lengthOfTop)/2 //the value of x in drawing above
        val topLineOffsetEnd = trapezoidWidth - topLineOffsetStart
        val bottomLeftCurveX = topLineOffsetStart/2
        val topRightCurveX = topLineOffsetEnd + (topLineOffsetStart/2)

        pathForCurve.moveTo(0f, trapezoidHeight)
        pathForCurve.cubicTo(bottomLeftCurveX, trapezoidHeight, bottomLeftCurveX, 0f, topLineOffsetStart, 0f)
        pathForCurve.lineTo(topLineOffsetEnd, 0f)
        pathForCurve.cubicTo(topRightCurveX, 0f, topRightCurveX, trapezoidHeight, trapezoidWidth, trapezoidHeight)
        canvas?.drawPath(pathForCurve, shapePaint)
    }
}