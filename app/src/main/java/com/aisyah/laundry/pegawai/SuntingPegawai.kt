package com.aisyah.laundry.pegawai

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelPegawai
import com.google.firebase.database.FirebaseDatabase

class SuntingPegawai : AppCompatActivity() {

    lateinit var etNama: EditText
    lateinit var etNoHP: EditText
    lateinit var etAlamat: EditText
    lateinit var etCabang: EditText
    private lateinit var idPegawai: String
    private var isEditing = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sunting_pegawai)

        etNama = findViewById(R.id.etnamapegawaisunting)
        etNoHP = findViewById(R.id.etNoHppegawaisunting)
        etAlamat = findViewById(R.id.etalamatpegawaisunting)
        etCabang = findViewById(R.id.etcabangpegawaisunting)

        idPegawai = intent.getStringExtra("idPegawai") ?: ""
        val nama = intent.getStringExtra("namaPegawai")
        val noHP = intent.getStringExtra("noHPPegawai")
        val alamat = intent.getStringExtra("alamatPegawai")
        val cabang = intent.getStringExtra("idCabang")

        etNama.setText(nama)
        etNoHP.setText(noHP)
        etAlamat.setText(alamat)
        etCabang.setText(cabang)

        // Matikan form dulu
        setFormEnabled(false)

        val btnSimpan = findViewById<Button>(R.id.buttonSuntingpegawai)
        btnSimpan.text = getString(R.string.btn_sunting)


        btnSimpan.setOnClickListener {
            if (!isEditing) {
                isEditing = true
                setFormEnabled(true)
                btnSimpan.text = getString(R.string.btn_simpan_perubahan)
            } else {
                updatePegawai()
            }
        }

    }

    private fun updatePegawai() {
        val updatedPegawai = ModelPegawai(
            idPegawai = idPegawai,
            namaPegawai = etNama.text.toString(),
            alamatPegawai = etAlamat.text.toString(),
            noHPPegawai = etNoHP.text.toString(),
            idCabang = etCabang.text.toString(),
            timestamp = System.currentTimeMillis()
        )

        val dbRef = FirebaseDatabase.getInstance().getReference("pegawai").child(idPegawai)
        dbRef.setValue(updatedPegawai)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.toast_sukses_update_pegawai), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.toast_gagal_update_pegawai), Toast.LENGTH_SHORT).show()
            }
    }
    private fun setFormEnabled(enabled: Boolean) {
        etNama.isEnabled = enabled
        etNoHP.isEnabled = enabled
        etAlamat.isEnabled = enabled
        etCabang.isEnabled = enabled
    }

}
