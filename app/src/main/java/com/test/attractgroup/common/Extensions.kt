package com.test.attractgroup.common

import android.app.Activity
import com.test.attractgroup.R

fun Activity.isTablet(): Boolean{
    return resources.getBoolean(R.bool.isTablet)
}