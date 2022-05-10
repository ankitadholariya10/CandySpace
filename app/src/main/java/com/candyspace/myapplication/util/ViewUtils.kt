package com.candyspace.myapplication.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds


@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String?) {
    Picasso.with(view.getContext())
        .load(imageUrl)
        .into(view)
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

@SuppressLint("SimpleDateFormat")
fun getDate(ts: Long?): String {
    if (ts == null) return ""
    val timeD = Date(ts * 1000)

    val sdf = SimpleDateFormat("dd MMM yyyy HH:MM:SS a")

    val time: String = sdf.format(timeD)
    Log.e("Date ===>", "" + timeD.time.hours + timeD.time.seconds + "Got")
    return time
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}
