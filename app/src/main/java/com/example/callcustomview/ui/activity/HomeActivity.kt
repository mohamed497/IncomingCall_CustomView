package com.example.callcustomview.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.callcustomview.utils.debug
import com.example.callcustomview.utils.toInVisible
import com.example.callcustomview.utils.toVisible
import com.mohamed.gamal.callcustomview.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var isSpeakButtonLongPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setOnLongClickListenerCallingBtn()
        setOnTouchListenerCallingBtn()

        callingBtn.setOnDragListener(dragListener)

    }

    private val dragListener = View.OnDragListener { view, dragEvent ->
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                debug("start Drag")
                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {

                view.invalidate()
                val v = dragEvent.localState as View
                // //
                val owner = v.parent as ViewGroup
                owner.removeView(v)
                // //
//                val destination = view as Button
                val destination = view as LinearLayout
                destination.addView(v)
                v.toVisible()
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }

    private fun setOnLongClickListenerCallingBtn() {
        callingBtn.setOnLongClickListener { view ->
            isSpeakButtonLongPressed = true
            acceptBtn.toVisible()
            rejectBtn.toVisible()
            msgBtn.toVisible()
            muteBtn.toVisible()
            val dragShadowBuilder = View.DragShadowBuilder(view)
            view.startDragAndDrop(null, dragShadowBuilder, view, 0)
            view.toInVisible()
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setOnTouchListenerCallingBtn() {
        callingBtn.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> debug("Down")
                MotionEvent.ACTION_UP -> {
                    acceptBtn.toInVisible()
                    rejectBtn.toInVisible()
                    msgBtn.toInVisible()
                    muteBtn.toInVisible()
                }

            }

            v?.onTouchEvent(event) ?: true
        }
    }

}