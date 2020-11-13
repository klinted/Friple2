package com.kodo.friple.mvvm.common.message

import android.content.Context
import android.net.Uri

data class MessageData(

    val imageUri: Uri?,
    val name: String,
    val whoSent: Boolean,
    val message: String,
    val time: String,
    val online: Boolean
)