package com.aisyah.laundry.layanan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelLayanan
import com.aisyah.laundry.modeldata.ModelPegawai
import com.google.firebase.database.FirebaseDatabase

class TambahLayanan : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("layanan")
    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etHarga: EditText
    lateinit var etCabang : EditText
    lateinit var btSimpan: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_layanan)
        init()
        btSimpan.setOnClickListener{
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init(){
        tvJudul=findViewById(R.id.tambahlayanan)
        etNama = findViewById(R.id.etnamalayanan)
        etCabang = findViewById(R.id.etcabanglayanan)
        etHarga=findViewById(R.id.ethargalayanan)
        btSimpan=findViewById(R.id.buttonSimpanlayanan)
    }
    fun cekValidasi() {
        val Nama = etNama.text.toString()
        val Harga = etHarga.toString()
        val cabang = etCabang.text.toString()

        if (Nama.isEmpty()) {
            etNama.error = this.getString(R.string._nama_pelanggan)
            Toast.makeText(this,this.getString(R.string._nama_pelanggan), Toast.LENGTH_SHORT)
                .show()
            etNama.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etCabang.error = this.getString(R.string._cabang_layanan)
            Toast.makeText(this, this.getString(R.string._cabang_layanan), Toast.LENGTH_SHORT)
                .show()
            etCabang.requestFocus()
            return
        }
        if (Harga.isEmpty()) {
            etCabang.error = this.getString(R.string._Harga_layanan)
            Toast.makeText(this, this.getString(R.string._Harga_layanan), Toast.LENGTH_SHORT)
                .show()
            etCabang.requestFocus()
            return
        }
        simpan()
    }
    fun simpan(){
        val layananBaru = myRef.push()
        val pegawaiId = layananBaru.key
        val data = ModelLayanan(
            pegawaiId.toString(),
            etNama.text.toString(),
            etCabang.text.toString(),
            etHarga.text.toString(),


            )
        layananBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.sukses_simpan_layanan),Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this,this.getString(R.string.gagal_simpan_layanan),Toast.LENGTH_SHORT).show()
            }
    }
}