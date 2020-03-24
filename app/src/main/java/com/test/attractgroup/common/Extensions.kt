package com.test.attractgroup.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import com.test.attractgroup.R
import android.text.format.DateFormat
import com.test.attractgroup.image_loading.ImageLoader
import java.time.Year
import java.util.*

fun Activity.isTablet(): Boolean {
    return resources.getBoolean(R.bool.isTablet)
}

fun View.show() {
    if (this.visibility != VISIBLE)
        this.visibility = VISIBLE
}

fun View.hide() {
    if (this.visibility != GONE)
        this.visibility = GONE
}

fun Long.getDateFormat(): String {
    val date = Date(this)
    val day = date.day
    val month = DateFormat.format("MMMM", date)
    val year = DateFormat.format("yyyy", date)
    val hours = DateFormat.format("hh", date)
    val minutes = DateFormat.format("mm", date)

    return "$day-$month-$year $hours:$minutes"
}

fun ImageView.loadImage(context: Context, url: String) {
    ImageLoader.load(this, url)
}

fun ImageView.loadImage(context: Context, url: String, defaultImage: Int) {

}