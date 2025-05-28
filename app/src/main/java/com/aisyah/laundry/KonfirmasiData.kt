package com.aisyah.laundry.Transaksi

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.adapter.AdapterTambahanDipilih
import com.aisyah.laundry.modeldata.ModelTambahan

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aisyah.laundry.R
import com.aisyah.laundry.adapter.AdapterKonfirmasiTambahan
import java.text.NumberFormat
import java.util.*

class KonfirmasiData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_data)

        val nama = intent.getStringExtra("namaPelanggan")?.substringAfter(": ")?.trim()
        val noHP = intent.getStringExtra("noHP")?.substringAfter(": ")?.trim()
        val namaLayanan = intent.getStringExtra("namaLayanan")?.substringAfter(": ")?.trim()
        val hargaLayanan = intent.getIntExtra("hargaLayanan", 0)
        val tambahanList = intent.getParcelableArrayListExtra<ModelTambahan>("tambahanList") ?: arrayListOf()

        findViewById<TextView>(R.id.namakonfirmasi_data).text = nama
        findViewById<TextView>(R.id.nohpkonfirmasi_data).text = noHP
        findViewById<TextView>(R.id.layanankonfirmasi_data).text = namaLayanan
        findViewById<TextView>(R.id.hargakonfirmasi_data).text = formatRupiah(hargaLayanan)

        // Tampilkan list layanan tambahan
        val rv = findViewById<RecyclerView>(R.id.rvKonfirmasiData)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = AdapterKonfirmasiTambahan(tambahanList)


        // Hitung total dari harga layanan + layanan tambahan
        val totalTambahan = tambahanList.sumOf { it.hargaTambahan ?: 0 } // ✅ Benar

        val totalBayar = hargaLayanan + totalTambahan

        findViewById<TextView>(R.id.tvTotalBayar).text = "Total Bayar: ${formatRupiah(totalBayar)}"
    }



    private fun formatRupiah(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        format.maximumFractionDigits = 0
        return format.format(number)
    }
}
