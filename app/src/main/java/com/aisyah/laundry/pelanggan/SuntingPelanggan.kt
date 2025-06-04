package com.aisyah.laundry.pelanggan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.FirebaseDatabase

class SuntingPelanggan : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etNoHP: EditText
    private lateinit var btnSunting: Button

    private var isEditing = false
    private lateinit var idPelanggan: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sunting_pelanggan)

        etNama = findViewById(R.id.etnamapelanggansunting)
        etAlamat = findViewById(R.id.etalamatpelanggansunting)
        etNoHP = findViewById(R.id.etNoHppelanggansunting)
        btnSunting = findViewById(R.id.buttonSuntingpelanggan)

        idPelanggan = intent.getStringExtra("idPelanggan") ?: ""
        etNama.setText(intent.getStringExtra("namaPelanggan"))
        etAlamat.setText(intent.getStringExtra("alamatPelanggan"))
        etNoHP.setText(intent.getStringExtra("noHPPelanggan"))

        setFormEnabled(false)
        btnSunting.text = "Sunting"

        btnSunting.setOnClickListener {
            if (!isEditing) {
                isEditing = true
                setFormEnabled(true)
                btnSunting.text = "Simpan Perubahan"
            } else {
                updatePelanggan()
            }
        }
    }

    private fun setFormEnabled(enabled: Boolean) {
        etNama.isEnabled = enabled
        etAlamat.isEnabled = enabled
        etNoHP.isEnabled = enabled
    }

    private fun updatePelanggan() {
        val updatedPelanggan = ModelPelanggan(
            idPelanggan = idPelanggan,
            namaPelanggan = etNama.text.toString(),
            alamatPelanggan = etAlamat.text.toString(),
            noHPPelanggan = etNoHP.text.toString()
        )

        val dbRef = FirebaseDatabase.getInstance().getReference("pelanggan").child(idPelanggan)
        dbRef.setValue(updatedPelanggan)
            .addOnSuccessListener {
                Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show()
            }
    }
}
