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
import com.google.firebase.database.FirebaseDatabase

class TambahPegawai : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pegawai")

    private lateinit var tvJudul: TextView
    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoHp: EditText
    private lateinit var etCabang: EditText
    private lateinit var btSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)

        initViews()
        setupView()
        setupButton()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViews() {
        tvJudul = findViewById(R.id.tambahpegawai)
        etNama = findViewById(R.id.etnamapegawai)
        etAlamat = findViewById(R.id.etalamatpegawai)
        etNoHp = findViewById(R.id.etNoHppegawai)
        etCabang = findViewById(R.id.etcabangpegawai)
        btSimpan = findViewById(R.id.buttonSimpanpegawai)
    }

    private fun setupView() {
        tvJudul.text = "Tambah Pegawai Baru"
        etNama.requestFocus()
    }

    private fun setupButton() {
        btSimpan.setOnClickListener {
            if (validateInput()) {
                saveEmployeeData()
            }
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true

        if (etNama.text.toString().trim().isEmpty()) {
            etNama.error = "Nama pegawai harus diisi"
            etNama.requestFocus()
            isValid = false
        }

        if (etAlamat.text.toString().trim().isEmpty()) {
            etAlamat.error = "Alamat harus diisi"
            if (isValid) etAlamat.requestFocus()
            isValid = false
        }

        if (etNoHp.text.toString().trim().isEmpty()) {
            etNoHp.error = "Nomor HP harus diisi"
            if (isValid) etNoHp.requestFocus()
            isValid = false
        }

        if (etCabang.text.toString().trim().isEmpty()) {
            etCabang.error = "Cabang harus diisi"
            if (isValid) etCabang.requestFocus()
            isValid = false
        }

        return isValid
    }

    private fun saveEmployeeData() {
        val employeeId = myRef.push().key ?: return

        val employee = ModelPegawai(
            idPegawai = employeeId,
            namaPegawai = etNama.text.toString(),
            alamatPegawai = etAlamat.text.toString(),
            noHPPegawai = etNoHp.text.toString(),
            idCabang = etCabang.text.toString(),
            timestamp = System.currentTimeMillis()
        )

        myRef.child(employeeId).setValue(employee)
            .addOnSuccessListener {
                Toast.makeText(this, "Data pegawai berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Gagal menyimpan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}