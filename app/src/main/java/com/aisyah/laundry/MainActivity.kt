package com.aisyah.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aisyah.laundry.Tambahan.DataTambahan
import com.aisyah.laundry.Tambahan.TambahLayananTambahan
import com.aisyah.laundry.Transaksi.Transaksi
import com.aisyah.laundry.layanan.DataLayanan
import com.aisyah.laundry.pegawai.DataPegawai
import com.aisyah.laundry.pelanggan.DataPelanggan


class MainActivity : AppCompatActivity() {

    lateinit var ivpelanggan : ImageView
    lateinit var cavpegawai : CardView
    lateinit var ivlayanan : ImageView
    lateinit var ivTransaksi : ImageView
    lateinit var ivTambahan : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ivTambahan = findViewById(R.id.ivtambahan)
        ivTransaksi = findViewById(R.id.ivTransaksi)
        ivpelanggan = findViewById(R.id.ivPelanggan)
        cavpegawai = findViewById(R.id.cvpegawai)
        ivlayanan = findViewById(R.id.ivlayanan)

        ivTambahan.setOnClickListener{
            val intent = Intent(this@MainActivity, DataTambahan::class.java)
            startActivity(intent)
        }
        ivTransaksi.setOnClickListener{
            val intent = Intent(this@MainActivity, Transaksi::class.java)
            startActivity(intent)
        }
        cavpegawai.setOnClickListener{
            val intent = Intent(this@MainActivity, DataPegawai::class.java)
            startActivity(intent)
        }

        ivpelanggan.setOnClickListener{
            val intent = Intent(this@MainActivity, DataPelanggan:: class.java)
            startActivity(intent)
        }

        ivlayanan.setOnClickListener{
            val intent = Intent(this@MainActivity, DataLayanan:: class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}