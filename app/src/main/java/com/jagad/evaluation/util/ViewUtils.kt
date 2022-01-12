package com.jagad.evaluation.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import java.lang.NullPointerException
import java.text.ParseException
import java.text.SimpleDateFormat

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun show(progressBar: ProgressBar){
    if (progressBar.visibility == View.GONE)
        progressBar.visibility = View.VISIBLE
}

fun hide(progressBar: ProgressBar){
    if (progressBar.visibility == View.VISIBLE)
        progressBar.visibility = View.GONE
}

fun changeDateFormat(inputDate:String,inputDateFormatPattern:String,outPutDateFormatPattern:String):String{
    val inputDateFormat = SimpleDateFormat(inputDateFormatPattern)
    val outPutDateFormat = SimpleDateFormat(outPutDateFormatPattern)
    try {
        val date = inputDateFormat.parse(inputDate)
        return outPutDateFormat.format(date)
    }catch (e: ParseException){
        e.printStackTrace()
    }catch (e:NullPointerException){
        e.printStackTrace()
    }
    return inputDate
}

fun Activity.hideKeyBoard(){
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}