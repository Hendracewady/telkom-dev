package com.telkom.android.telkomdevtest.util

import android.os.Build
import android.text.Html
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Hendra Cewady on 19/01/2022.
 */
object ApplicationUtil {

    fun convertMillisToData(format: String, millis: Long): String {
        var sdf = SimpleDateFormat("dd/MM/yyyy")
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        return sdf.format(calendar.time)
    }

    fun convertHtmlToString(htmlString: String): String {
        var result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(htmlString).toString()
        }
        return result
    }

}