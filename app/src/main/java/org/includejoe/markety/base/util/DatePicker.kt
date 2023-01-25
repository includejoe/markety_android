package org.includejoe.markety.base.util

import android.app.DatePickerDialog
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

class DatePicker(
    val context: Context,
    var date: String,
    val setDate: (String) -> Unit
) {
    private var dateFormat = "dd-MM-yyyy"

    fun showDatePickerDialog() {
        val calendar = getCalender()
        DatePickerDialog(
            context, {_, year, month, day ->
                date = getPickedDateAsString(year, month, day)
                setDate(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun getCalender(): Calendar {
        return if (date.isEmpty())
            Calendar.getInstance()
        else
            getLastPickedDateCalendar()
    }

    private fun getLastPickedDateCalendar(): Calendar {
        val dateFormat = SimpleDateFormat(dateFormat, Locale.UK)
        val calendar = Calendar.getInstance()
        calendar.time = dateFormat.parse(date) as Date
        return calendar
    }

    private fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat(dateFormat, Locale.UK)
        return dateFormat.format(calendar.time)
    }
}