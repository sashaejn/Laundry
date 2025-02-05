package com.aisyah.laundry.pelanggan

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aisyah.laundry.R
import com.aisyah.laundry.pegawai.DataPegawai
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DataPelanggan : AppCompatActivity() {
    lateinit var tambah : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pelanggan)

        tambah = findViewById(R.id.fabDATA_PENGGUNA_Tambah)
        tambah.setOnClickListener{
            val intent = Intent(this@DataPelanggan, TambahPelanggan::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}