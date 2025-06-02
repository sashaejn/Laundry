package com.aisyah.laundry

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.adapter.AdapterKonfirmasiTambahan
import com.aisyah.laundry.modeldata.ModelTambahan
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class pembayaranAkhir_Activity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterKonfirmasiTambahan

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran_akhir)

        // Ambil data dari intent
        val nama = intent.getStringExtra("nama")
        val noHp = intent.getStringExtra("noHp")
        val namaLayanan = intent.getStringExtra("layanan")
        val hargaLayanan = intent.getIntExtra("hargaLayanan", 0)
        val totalTambahan = intent.getIntExtra("subtotalTambahan", 0)
        val totalBayar = intent.getIntExtra("totalBayar", 0)
        val listTambahan = intent.getParcelableArrayListExtra<ModelTambahan>("layananTambahanList")

        val idTransaksi = intent.getStringExtra("idTransaksi")

        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") // Contoh: 02 Jun 2025 14:30:45
        val formattedDateTime = currentDateTime.format(formatter)
        val waktuTextView = findViewById<TextView>(R.id.waktupembayaranPA)
        waktuTextView.text = formattedDateTime

// Set ke TextView






        findViewById<TextView>(R.id.idpembayaranakhir).text = idTransaksi

        // Set teks ke TextView
        findViewById<TextView>(R.id.namapembayaranakhir).text = "Laundry Aisyah"
        findViewById<TextView>(R.id.alamatpa).text = "Solo"
        findViewById<TextView>(R.id.pesananpembayaranakhir).text = nama
        findViewById<TextView>(R.id.hargalayananPA).text = formatRupiah(hargaLayanan)
        findViewById<TextView>(R.id.subtotal).text = formatRupiah(totalTambahan)
        findViewById<TextView>(R.id.subtotalharga).text = formatRupiah(totalBayar)

        // RecyclerView
        recyclerView = findViewById(R.id.rvpembayaranakhir)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterKonfirmasiTambahan(listTambahan ?: arrayListOf())
        recyclerView.adapter = adapter
    }

    private fun formatRupiah(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        format.maximumFractionDigits = 0
        return format.format(number)

    }


}
