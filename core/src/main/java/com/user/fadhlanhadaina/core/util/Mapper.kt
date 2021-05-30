package com.user.fadhlanhadaina.core.util

import com.user.fadhlanhadaina.core.data.source.remote.response.LoginResponse
import com.user.fadhlanhadaina.core.domain.model.User

object Mapper {
    fun LoginResponse.toUser(): User = User(this.username, this.email, this.password)
    fun mapHeaderDateToDate(date: String): String {
        val arrayString = date.split(" ")
        val day = arrayString[1].subSequence(0, arrayString[1].length-1)
        return "${if(day.length == 1) "0$day" else day}/${mapMonthNameToMonth(arrayString[0])}/${arrayString[2]}"
    }
    private fun mapMonthNameToMonth(s: String): String {
        return when(s) {
            "Jan" -> "01"
            "Feb" -> "02"
            "Mar" -> "03"
            "Apr" -> "04"
            "May" -> "05"
            "Jun" -> "06"
            "Jul" -> "07"
            "Aug" -> "08"
            "Sep" -> "09"
            "Oct" -> "10"
            "Nov" -> "11"
            "Dec" -> "12"
            else -> "-"
        }
    }
    fun mapRequestStatusToInfo(s: String): String {
        return when {
            s.contains("401") -> "Invalid credential"
            else -> s
        }
    }
}