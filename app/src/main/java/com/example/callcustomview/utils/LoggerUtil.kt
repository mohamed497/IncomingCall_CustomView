package com.example.callcustomview.utils

import android.util.Log
import android.view.View


fun Any.debug(message: Any) {
    Log.d(this::class.java.simpleName, message.toString())
}



