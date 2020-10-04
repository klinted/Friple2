package com.kodo.friple.mvvm.model.models

import android.os.Handler

class RegModel {

        fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
            Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
        }

    interface OnDataReadyCallback {
        fun onDataReady(data : String)
    }
}