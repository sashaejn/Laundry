package com.aisyah.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aisyah.laundry.Tambahan.DataTambahan
import com.aisyah.laundry.Transaksi.Transaksi
import com.aisyah.laundry.layanan.DataLayanan
import com.aisyah.laundry.pegawai.DataPegawai
import com.aisyah.laundry.pelanggan.DataPelanggan
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var ivpelanggan: ImageView
    lateinit var cavpegawai: CardView
    lateinit var ivlayanan: ImageView
    lateinit var ivTransaksi: ImageView
    lateinit var ivTambahan: ImageView
    lateinit var tvGreeting: TextView
    lateinit var tvTanggal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        ivTambahan = findViewById(R.id.ivtambahan)
        ivTransaksi = findViewById(R.id.ivTransaksi)
        ivpelanggan = findViewById(R.id.ivPelanggan)
        cavpegawai = findViewById(R.id.cvpegawai)
        ivlayanan = findViewById(R.id.ivlayanan)
        tvGreeting = findViewById(R.id.selamat)
        tvTanggal = findViewById(R.id.tanggal)

        // Tampilkan ucapan dan tanggal
        setGreetingAndDate()

        // Navigasi
        ivTambahan.setOnClickListener {
            startActivity(Intent(this, DataTambahan::class.java))
        }
        ivTransaksi.setOnClickListener {
            startActivity(Intent(this, Transaksi::class.java))
        }
        cavpegawai.setOnClickListener {
            startActivity(Intent(this, DataPegawai::class.java))
        }
        ivpelanggan.setOnClickListener {
            startActivity(Intent(this, DataPelanggan::class.java))
        }
        ivlayanan.setOnClickListener {
            startActivity(Intent(this, DataLayanan::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setGreetingAndDate() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = when (hour) {
            in 4..10 -> "Selamat Pagi, Aisyah"
            in 11..14 -> "Selamat Siang, Aisyah"
            in 15..17 -> "Selamat Sore, Aisyah"
            else -> "Selamat Malam, Aisyah"
        }

        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        val today = sdf.format(calendar.time)

        tvGreeting.text = greeting
        tvTanggal.text = today
    }
}
