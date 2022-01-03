package com.example.callcustomview.ui.customview.incomingcall

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.mohamed.gamal.callcustomview.R

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.callcustomview.ui.customview.incomingcall.IncomingCallConstants.CANVAS_LEFT
import com.example.callcustomview.ui.customview.incomingcall.IncomingCallConstants.CANVAS_TOP
import com.example.callcustomview.ui.customview.incomingcall.IncomingCallConstants.DEFAULT_CANVAS_LEFT
import com.example.callcustomview.ui.customview.incomingcall.IncomingCallConstants.DEFAULT_CANVAS_TOP


class IncomingCallView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    @SuppressLint("UseCompatLoadingForDrawables")
    private val ringingDrawableDefaultValue =
        resources.getDrawable(R.drawable.ic_baseline_calling_24, null)

    private val ringingBtnBitmap = getBitmap(
        ContextCompat.getDrawable(
            context,
            R.drawable.ic_baseline_calling_24
        ) ?: ringingDrawableDefaultValue
    )

    @SuppressLint("UseCompatLoadingForDrawables")
    private val acceptDrawableDefaultValue =
        resources.getDrawable(R.drawable.ic_baseline_call_24, null)
    private val acceptButtonDrawable =
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_call_24)
    private val acceptBtnBitmap =
        getBitmap(acceptButtonDrawable ?: acceptDrawableDefaultValue)


    @SuppressLint("UseCompatLoadingForDrawables")
    private val rejectDrawableDefaultValue =
        resources.getDrawable(R.drawable.ic_baseline_call_end_24, null)
    private val rejectBtnDrawable =
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_call_end_24)
    private val rejectBtnBitmap =
        getBitmap(rejectBtnDrawable ?: rejectDrawableDefaultValue)

    @SuppressLint("UseCompatLoadingForDrawables")
    private val msgDrawableDefaultValue =
        resources.getDrawable(R.drawable.ic_baseline_message_24, null)
    private val msgBtnDrawable =
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_message_24)
    private val msgBtnBitmap =
        getBitmap(msgBtnDrawable ?: msgDrawableDefaultValue)

    @SuppressLint("UseCompatLoadingForDrawables")
    private val muteDrawableDefaultValue =
        resources.getDrawable(R.drawable.ic_baseline_volume_mute_24, null)
    private val muteButtonDrawable =
        ContextCompat.getDrawable(context, R.drawable.ic_baseline_volume_mute_24)
    private val muteBtnBitmap =
        getBitmap(muteButtonDrawable ?: muteDrawableDefaultValue)


    private var xPosition = IncomingCallConstants.X_POSITION
    private var yPosition = IncomingCallConstants.Y_POSITION

    private var buttonState = 0

    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(DEFAULT_CANVAS_LEFT, DEFAULT_CANVAS_TOP, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.IncomingCallView) {
            buttonState = getInt(R.styleable.IncomingCallView_callStatus, 0)
            xPosition = getFloat(
                R.styleable.IncomingCallView_x_position,
                IncomingCallConstants.X_POSITION
            )
            yPosition = getFloat(
                R.styleable.IncomingCallView_y_position,
                IncomingCallConstants.Y_POSITION
            )
        }

    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setupBtnPosition()
        drawBtn(canvas)
    }


    private fun setupBtnPosition() {
        translationX = (measuredWidth * xPosition) - 300
        translationY = (measuredHeight * yPosition) - 300
    }

    private fun drawBtn(canvas: Canvas) {
        when (buttonState) {
            0 -> {
                canvas.drawBitmap(
                    ringingBtnBitmap,
                    CANVAS_LEFT,
                    CANVAS_TOP,
                    paint
                )
            }
            1 -> {
                canvas.drawBitmap(
                    acceptBtnBitmap,
                    CANVAS_LEFT,
                    CANVAS_TOP,
                    paint
                )
            }
            2 -> {
                canvas.drawBitmap(
                    rejectBtnBitmap,
                    CANVAS_LEFT,
                    CANVAS_TOP,
                    paint
                )
            }
            4 -> {
                canvas.drawBitmap(
                    msgBtnBitmap,
                    CANVAS_LEFT,
                    CANVAS_TOP,
                    paint
                )
            }
            3 -> {
                canvas.drawBitmap(
                    muteBtnBitmap,
                    CANVAS_LEFT,
                    CANVAS_TOP,
                    paint
                )
            }
        }
    }

}
