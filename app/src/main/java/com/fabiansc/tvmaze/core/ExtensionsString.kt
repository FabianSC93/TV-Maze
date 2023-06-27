package com.fabiansc.tvmaze.core

import androidx.core.text.HtmlCompat

fun String.convertToHtml(): String = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
