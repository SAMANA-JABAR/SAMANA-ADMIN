package com.user.fadhlanhadaina.core.domain.model

import com.google.gson.annotations.SerializedName

class Bantuan constructor(
    private var nik: String,
    private var nama: String,
    @SerializedName("tgl_lahir")
    private var tglLahir: String,
    private var tanggungan: Int,
    private var pendidikan: String,
    private var profesi: String,
    private var status: String,
    private var gaji: String,
    private var kota: String,
    private var kecamatan: String,
    private var kelurahan: String,
    private var rt: String,
    private var rw: String,
    private var alamat: String,
    private var bantuan: String,
    private var tahap: Int,
    private var kesehatan: String,
    private var atap: String,
    private var dinding: String,
    private var lantai: String,
    private var penerangan: String,
    private var air: String,
    @SerializedName("luas_rumah")
    private var luasRumah: String,
)