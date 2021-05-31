package com.user.fadhlanhadaina.core.util

import androidx.core.text.isDigitsOnly
import com.user.fadhlanhadaina.core.data.source.remote.response.BantuanResponse
import com.user.fadhlanhadaina.core.data.source.remote.response.LoginResponse
import com.user.fadhlanhadaina.core.domain.model.Bantuan
import com.user.fadhlanhadaina.core.domain.model.User

object Mapper {
    fun LoginResponse.toUser(): User = User(this.name, this.username, this.email, this.password)

    fun Bantuan.mapToHashMap(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["nik"] = this.nik ?: ""
        map["nama"] = this.nama ?: ""
        map["tanggal_lahir"] = this.tglLahir ?: ""
        map["tanggungan_keluarga"] = this.tanggungan ?: ""
        map["pendidikan"] = this.pendidikan ?: ""
        map["profesi"] = this.profesi ?: ""
        map["status"] = this.status ?: ""
        map["gaji"] = this.gaji ?: ""
        map["kota_kabupaten"] = this.kota ?: ""
        map["kecamatan"] = this.kecamatan ?: ""
        map["kelurahan"] = this.kelurahan ?: ""
        map["rt"] = this.rt ?: ""
        map["rw"] = this.rw ?: ""
        map["alamat"] = this.alamat ?: ""
        map["kesehatan"] = this.kesehatan ?: ""
        map["atap"] = this.atap ?: ""
        map["dinding"] = this.dinding ?: ""
        map["lantai"] = this.lantai ?: ""
        map["penerangan"] = this.penerangan ?: ""
        map["air"] = this.air ?: ""
        map["luas_rumah"] = this.luasRumah ?: ""
        return map
    }

    fun BantuanResponse.mapToBantuan() = Bantuan(
        nik, nama, tglLahir, tanggungan, pendidikan, profesi, status, gaji,
        kota, kecamatan, kelurahan, rt, rw, alamat,
        kesehatan, atap, dinding, lantai, penerangan, air, luasRumah, validasi
    )

    fun mapHeaderDateToDate(date: String): String {
        val arrayString = date.split(" ")
        val day = when {
            arrayString[0].isDigitsOnly() -> arrayString[0]
            else -> arrayString[1].subSequence(0, arrayString[1].length-1)
        }
        val monthName = when {
            !arrayString[0].isDigitsOnly() -> arrayString[0]
            else -> arrayString[1]
        }
        val month = mapMonthNameToMonth(monthName)
        val year = arrayString[2]
        return "${if(day.length == 1) "0$day" else day}/$month/$year"
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
            s.contains("HTTP 401") -> "Invalid credential"
            else -> s
        }
    }
}