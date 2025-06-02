package com.aisyah.laundry.Transaksi

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.adapter.AdapterKonfirmasiTambahan
import com.aisyah.laundry.modeldata.ModelTambahan
import com.aisyah.laundry.pembayaranAkhir_Activity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class KonfirmasiData : AppCompatActivity() {

    private var nama: String? = null
    private var noHP: String? = null
    private var namaLayanan: String? = null
    private var hargaLayanan: Int = 0
    private var tambahanList: ArrayList<ModelTambahan> = arrayListOf()
    private var totalBayar: Int = 0
    private var totalTambahan: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_data)

        // Ambil data dari intent
        nama = intent.getStringExtra("namaPelanggan")?.substringAfter(": ")?.trim()
        noHP = intent.getStringExtra("noHP")?.substringAfter(": ")?.trim()
        namaLayanan = intent.getStringExtra("namaLayanan")?.substringAfter(": ")?.trim()
        hargaLayanan = intent.getIntExtra("hargaLayanan", 0)
        tambahanList = intent.getParcelableArrayListExtra("tambahanList") ?: arrayListOf()

        // Tampilkan data
        findViewById<TextView>(R.id.namakonfirmasi_data).text = nama
        findViewById<TextView>(R.id.nohpkonfirmasi_data).text = noHP
        findViewById<TextView>(R.id.layanankonfirmasi_data).text = namaLayanan
        findViewById<TextView>(R.id.hargakonfirmasi_data).text = formatRupiah(hargaLayanan)

        // Hitung total tambahan dan total bayar
        totalTambahan = tambahanList.sumOf { it.hargaTambahan ?: 0 }
        totalBayar = hargaLayanan + totalTambahan
        findViewById<TextView>(R.id.tvTotalBayar).text = "Total Bayar: ${formatRupiah(totalBayar)}"

        // Setup RecyclerView
        val rv = findViewById<RecyclerView>(R.id.rvKonfirmasiData)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = AdapterKonfirmasiTambahan(tambahanList)

        // Tombol
        findViewById<Button>(R.id.btbatalKD).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btpembayaranKD).setOnClickListener {
            showMetodePembayaranDialog()
        }
    }

    private fun showMetodePembayaranDialog() {
        val dialog = Dialog(this)
        val view = layoutInflater.inflate(R.layout.activity_metode_pembayaran, null)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        dialog.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window?.setGravity(Gravity.CENTER)

        val metodeButtons = mapOf(
            R.id.btnBayarNanti to "Bayar Nanti",
            R.id.btnTunai to "Tunai",
            R.id.btnQRis to "QRIS",
            R.id.btnDana to "DANA",
            R.id.btnGoPay to "GoPay",
            R.id.btnOvo to "OVO"
        )

        for ((id, metode) in metodeButtons) {
            view.findViewById<Button>(id).setOnClickListener {
                Toast.makeText(this, "Metode $metode dipilih", Toast.LENGTH_SHORT).show()
                lanjutKePembayaran(metode)
                dialog.dismiss()
            }
        }

        view.findViewById<TextView>(R.id.TvBatal).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun lanjutKePembayaran(metode: String) {
        val waktuSekarang = Date()

        val formatterTanggal = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val formatterWaktu = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val tanggalPembayaran = formatterTanggal.format(waktuSekarang)
        val waktuPembayaran = formatterWaktu.format(waktuSekarang)

        val formatterID = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault())
        val idTransaksi = "TRX${formatterID.format(waktuSekarang)}"

        val intent = Intent(this, pembayaranAkhir_Activity::class.java).apply {
            putExtra("idTransaksi", idTransaksi)
            putExtra("nama", nama)
            putExtra("noHp", noHP)
            putExtra("layanan", namaLayanan)
            putExtra("hargaLayanan", hargaLayanan)
            putExtra("subtotalTambahan", totalTambahan)
            putExtra("totalBayar", totalBayar)
            putExtra("metodePembayaran", metode)
            putExtra("tanggalPembayaran", tanggalPembayaran)
            putExtra("waktuPembayaran", waktuPembayaran)
            putParcelableArrayListExtra("layananTambahanList", tambahanList)
        }
        startActivity(intent)
    }



    private fun formatRupiah(number: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        format.maximumFractionDigits = 0
        return format.format(number)
    }
}
