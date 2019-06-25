package com.caique.atlasandroidrecruitment.extension

import com.caique.atlasandroidrecruitment.utils.Constants.Companion.LOCAL_DATE_FORMAT
import com.caique.atlasandroidrecruitment.utils.Constants.Companion.SERVER_DATE_FORMAT
import java.text.SimpleDateFormat

fun String.formatDate(): String {
    return if (this.length > 10) {
        val date = this.substring(0, 10)
        var sd = SimpleDateFormat(SERVER_DATE_FORMAT)
        val d = sd.parse(date)
        sd = SimpleDateFormat(LOCAL_DATE_FORMAT)
        sd.format(d)
    } else {
        this
    }
}