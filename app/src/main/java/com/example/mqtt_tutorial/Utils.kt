/*
 * Copyright (c) 2021 CodingWithChris - All Rights Reserved
 */

package com.example.mqtt_tutorial

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun getStringFromDate(date: Date = Date(), pattern:String = "dd.MM.yyyy - hh:mm:ss"): String {
    return SimpleDateFormat(pattern).format(date)
}



fun printToast(context: Context, msg:String)
{
    Toast.makeText(context,msg, Toast.LENGTH_LONG).apply {
        setGravity(Gravity.CENTER,0,0)
    }.show()
}