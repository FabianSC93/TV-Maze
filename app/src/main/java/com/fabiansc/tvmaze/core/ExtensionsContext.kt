package com.fabiansc.tvmaze.core

import android.content.Context
import android.content.res.Configuration

fun Context.isTablet() =
    (this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE

