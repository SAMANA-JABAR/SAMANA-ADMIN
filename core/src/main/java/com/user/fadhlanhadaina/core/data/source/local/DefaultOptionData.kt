package com.user.fadhlanhadaina.core.data.source.local

object DefaultOptionData {
    fun generateListTanggungan(): List<String> = listOf("0", "1", "2", "3", "4", "5", ">5")
    fun generateListPendidikan(): List<String> = listOf(
        "Tidak ada",
        "SD",
        "SMP",
        "SMA",
        "D3/D4",
        "S1",
        "S2",
        "S3",
    )
    fun generateListProfesi(): List<String> = listOf(
        "BELUM/TIDAK BEKERJA",
        "MENGURUS RUMAH TANGGA",
        "PELAJAR/MAHASISWA",
        "PENSIUNAN",
        "PEGAWAI NEGERI SIPIL",
        "TENTARA NASIONAL INDONESIA",
        "KEPOLISIAN RI",
        "PERDAGANGAN",
        "PETANI/PEKEBUN",
        "PETERNAK",
        "NELAYAN/PERIKANAN",
        "INDUSTRI",
        "KONSTRUKSI",
        "TRANSPORTASI",
        "KARYAWAN SWASTA",
        "KARYAWAN BUMN",
        "KARYAWAN BUMD",
        "KARYAWAN HONORER",
        "BURUH HARIAN LEPAS",
        "BURUH TANI/PERKEBUNAN",
        "BURUH NELAYAN/PERIKANAN",
        "BURUH PETERNAKAN",
        "PEMBANTU RUMAH TANGGA",
        "TUKANG CUKUR",
        "TUKANG LISTRIK",
        "TUKANG BATU",
        "TUKANG KAYU",
        "TUKANG SOL SEPATU",
        "TUKANG LAS/PANDAI BESI",
        "TUKANG JAHIT",
        "TUKANG GIGI",
        "PENATA RIAS",
        "PENATA BUSANA",
        "PENATA RAMBUT",
        "MEKANIK",
        "SENIMAN",
        "TABIB",
        "PARAJI",
        "PERANCANG BUSANA",
        "PENTERJEMAH",
        "IMAM MESJID",
        "PENDETA",
        "PASTOR",
        "WARTAWAN",
        "USTADZ/MUBALIGH",
        "JURU MASAK",
        "PROMOTOR ACARA",
        "ANGGOTA DPR-RI",
        "ANGGOTA DPD",
        "ANGGOTA BPK",
        "PRESIDEN",
        "WAKIL PRESIDEN",
        "ANGGOTA MAHKAMAH KONSTITUSI",
        "ANGGOTA KABINET/KEMENTERIAN",
        "DUTA BESAR",
        "GUBERNUR",
        "WAKIL GUBERNUR",
        "BUPATI",
        "WAKIL BUPATI",
        "WALIKOTA",
        "WAKIL WALIKOTA",
        "ANGGOTA DPRD PROVINSI",
        "ANGGOTA DPRD KABUPATEN/KOTA",
        "DOSEN",
        "GURU",
        "PILOT",
        "PENGACARA",
        "NOTARIS",
        "ARSITEK",
        "AKUNTAN",
        "KONSULTAN",
        "DOKTER",
        "BIDAN",
        "PERAWAT",
        "APOTEKER",
        "PSIKIATER/PSIKOLOG",
        "PENYIAR TELEVISI",
        "PENYIAR RADIO",
        "PELAUT",
        "PENELITI",
        "SOPIR",
        "PIALANG",
        "PARANORMAL",
        "PEDAGANG",
        "PERANGKAT DESA",
        "KEPALA DESA",
        "BIARAWATI",
        "WIRASWASTA",
    )
    fun generateListStatusKerja(): List<String> = listOf(
        "Belum/tidak bekerja",
        "Pensiunan",
        "Pekerja tidak tetap",
        "Pekerja tetap",
    )
    fun generateListGaji(): List<String> = listOf(
        "0-500.000",
        "500.000 - 1.000.000",
        "1.000.000 - 2.500.000",
        "2.500.000 - 4.000.000",
        "4.000.000 - 6.500.000",
        "> 6.500.000",
    )
    fun generateListKesehatan(): List<String> = listOf(
        "Sehat",
        "Penyandang disabilitas",
        "Penyakit kronis"
    )
    fun generateListAtap(): List<String> = listOf(
        "Atap kualitas baik",
        "Atap kualitas rendah/usang",
        "Atap ijuk/rumbia",
        "Tidak beratap"
    )
    fun generateListDinding(): List<String> = listOf(
        "Tembok Kualitas Baik",
        "Tembok Kualitas Rendah/Usang",
        "Tembok Tidak Diplester",
        "Dinding Bambu/Kayu",
        "Tidak Berdinding"
    )
    fun generateListLantai(): List<String> = listOf(
        "Keramik kualitas baik",
        "Keramik kualitas rendah/usang",
        "Kayu/Semen",
        "Tanah"
    )
    fun generateListPenerangan(): List<String> = listOf(
        "Listrik PLN",
        "Listrik PLN sambung (bayar)",
        "Listrik PLN sambung (gratis)",
        "Tidak ada listrik"
    )
    fun generateListAir(): List<String> = listOf(
        "PDAM",
        "Sumur bor",
        "Mata air alami"
    )
    fun generateListLuasRumah(): List<String> = listOf(
        ">36 m2",
        "21-36 m2",
        "8-21 m2",
        "0-8 m2",
    )
}