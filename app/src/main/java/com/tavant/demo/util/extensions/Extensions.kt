package com.tavant.demo.util.extensions

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.toastMessage(msg:String) = Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}