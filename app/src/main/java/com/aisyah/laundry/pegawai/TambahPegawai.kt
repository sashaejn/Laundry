package com.aisyah.laundry.pegawai

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
import com.aisyah.laundry.modeldata.ModelPegawai
import com.aisyah.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.FirebaseDatabase

class TambahPegawai : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etNoHp : EditText
    lateinit var etCabang : EditText
    lateinit var btSimpan: Button

    var idPegawai:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
        init()
        getData()
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
        tvJudul=findViewById(R.id.tambahpegawai)
        etNama = findViewById(R.id.etnamapegawai)
        etAlamat=findViewById(R.id.etalamatpegawai)
        etNoHp = findViewById(R.id.etNoHppegawai)
        etCabang = findViewById(R.id.etcabangpegawai)
        btSimpan=findViewById(R.id.buttonSimpanpegawai)
    }
    fun cekValidasi() {
        val Nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHp.text.toString()
        val cabang = etCabang.text.toString()

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
        if (cabang.isEmpty()) {
           etCabang.error = this.getString(R.string._noHp_pelanggan)
            Toast.makeText(this, this.getString(R.string._noHp_pelanggan), Toast.LENGTH_SHORT)
                .show()
            etCabang.requestFocus()
            return
        }
        simpan()
    }
    fun simpan(){
        val pegawaiBaru = myRef.push()
        val pegawaiId = pegawaiBaru.key
        val data = ModelPegawai(
            pegawaiId.toString(),
            etNama.text.toString(),
            etCabang.text.toString(),
            etAlamat.text.toString(),
            etNoHp.text.toString(),


        )
        pegawaiBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.sukses_simpan_pelanggan),Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this,this.getString(R.string.gagal_simpan_pelanggan),Toast.LENGTH_SHORT).show()
            }
    }

    fun getData(){
        idPegawai = intent.getStringExtra("idPegawai").toString()
        val judul = intent.getStringExtra("judul")
        val nama = intent.getStringExtra("namaPegawai")
        val alamat = intent.getStringExtra("alamatPegawai")
        val hp = intent.getStringExtra("noHPPegawai")
        val cabang= intent.getStringExtra("idCabang")
        tvJudul.text = judul
        etNama.setText(nama)
        etAlamat.setText(alamat)
        etNoHp.setText(hp)
        etCabang.setText(cabang)
        if(!tvJudul.text.equals(this.getString(R.string.pegawai_tambah))) {
            if (judul.equals("Edit Pegawai")) {
                mati()
                btSimpan.text = "Sunting"
            }
            }else{
                hidup()
                etNama.requestFocus()
                btSimpan.text="Simpan"
        }
    }
    fun mati(){
        etNama.isEnabled=false
        etAlamat.isEnabled=false
        etNoHp.isEnabled=false
        etCabang.isEnabled=false
    }
    fun hidup(){
        etNama.isEnabled=true
        etAlamat.isEnabled=true
        etNoHp.isEnabled=true
        etCabang.isEnabled=true
    }

    fun update(){
        val pegawaiRef = database.getReference("pegawai").child(idPegawai)

        //update map untuk uodate data
        val updateData = mutableMapOf<String,Any>()
        updateData["namaPegawai"] = etNama.text.toString()
        updateData["alamatPegawai"] = etAlamat.text.toString()
        updateData["noHPPegawai"] = etNoHp.text.toString()
        updateData["idCabang"] = etCabang.text.toString()
        pegawaiRef.updateChildren(updateData).addOnSuccessListener {
            Toast.makeText(this@TambahPegawai,"Data Pegawai Berhasil Diperbarui", Toast.LENGTH_SHORT)
            finish()
        }.addOnFailureListener{
            Toast.makeText(this@TambahPegawai,"Data Pegawai Gagal Diperbarui", Toast.LENGTH_SHORT)
        }
        if (btSimpan.text.equals("Simpan")){
            simpan()
        }else if(btSimpan.text.equals("Sunting")){
            hidup()
            etNama.requestFocus()
            btSimpan.text="Perbarui"
        }else if(btSimpan.text.equals("Perbarui")){
            update()
        }
    }
}