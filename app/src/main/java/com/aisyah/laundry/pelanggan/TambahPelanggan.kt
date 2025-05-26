package com.aisyah.laundry.pelanggan

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
import com.aisyah.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.FirebaseDatabase

class TambahPelanggan : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")
    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etAlamat:EditText
    lateinit var etNoHp : EditText
    lateinit var btSimpan:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
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
        tvJudul=findViewById(R.id.tambahpelanggan)
        etNama = findViewById(R.id.etnamapelanggan)
        etAlamat=findViewById(R.id.etalamatpelanggan)
        etNoHp = findViewById(R.id.etNoHppelanggan)
        btSimpan=findViewById(R.id.buttonSimpanpelanggan)
    }

    fun cekValidasi() {
        val Nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHp.text.toString()
//        val cabang = etCabang.text.toString()

        if (Nama.isEmpty()) {
            etNama.error = this.getString(R.string._nama_pelanggan)
            Toast.makeText(this,this.getString(R.string._nama_pelanggan), Toast.LENGTH_SHORT)
                .show()
            etNama.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = this.getString(R.string._alamat_pelanggan)
            Toast.makeText(this,this.getString(R.string._alamat_pelanggan), Toast.LENGTH_SHORT)
                .show()
            etAlamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etNoHp.error = this.getString(R.string._noHp_pelanggan)
            Toast.makeText(this, this.getString(R.string._noHp_pelanggan), Toast.LENGTH_SHORT)
                .show()
            etNoHp.requestFocus()
            return
        }
        simpan()
    }

    fun simpan(){
        val pelangganBaru = myRef.push()
        val pelangganId = pelangganBaru.key
        val data = ModelPelanggan(
            pelangganId.toString(),
            etNama.text.toString(),
            etAlamat.text.toString(),
            etNoHp.text.toString()
//           / etCabang.text.toString()

        )
        pelangganBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.sukses_simpan_pelanggan),Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this,this.getString(R.string.gagal_simpan_pelanggan),Toast.LENGTH_SHORT).show()
            }
    }
}