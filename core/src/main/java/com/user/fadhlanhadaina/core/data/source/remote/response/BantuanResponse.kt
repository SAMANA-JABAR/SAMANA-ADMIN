package com.user.fadhlanhadaina.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BantuanResponse(
    var nik: String?,
    var nama: String?,
    @SerializedName("tanggal_lahir")
    var tglLahir: String?,
    @SerializedName("tanggungan_keluarga")
    var tanggungan: String?,
    var pendidikan: String?,
    var profesi: String?,
    var status: String?,
    var gaji: String?,
    @SerializedName("kota_kabupaten")
    var kota: String?,
    var kecamatan: String?,
    var kelurahan: String?,
    var rt: String?,
    var rw: String?,
    var alamat: String?,
    var kesehatan: String?,
    var atap: String?,
    var dinding: String?,
    var lantai: String?,
    var penerangan: String?,
    var air: String?,
    @SerializedName("luas_rumah")
    var luasRumah: String?,
    var validasi: String?,
)
