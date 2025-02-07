package com.aisyah.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aisyah.laundry.pegawai.DataPegawai
import com.aisyah.laundry.pelanggan.DataPelanggan


class MainActivity : AppCompatActivity() {

    lateinit var ivpelanggan : ImageView
    lateinit var cavpegawai : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ivpelanggan = findViewById(R.id.ivPelanggan)
        cavpegawai = findViewById(R.id.cvpegawai)

        cavpegawai.setOnClickListener{
            val intent = Intent(this@MainActivity, DataPegawai::class.java)
            startActivity(intent)
        }

        ivpelanggan.setOnClickListener{
            val intent = Intent(this@MainActivity, DataPelanggan:: class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}