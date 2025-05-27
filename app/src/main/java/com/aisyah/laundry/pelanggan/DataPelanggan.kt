package com.aisyah.laundry.pelanggan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.adapter.DataPelangganAdapter
import com.aisyah.laundry.modeldata.ModelPelanggan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataPelanggan : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("pelanggan")

    private lateinit var rvdataPelanggan: RecyclerView
    private lateinit var fabDATA_PENGGUNA_Tambah: FloatingActionButton
    private lateinit var pelangganList: ArrayList<ModelPelanggan>
    private lateinit var tvDataKosong: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pelanggan)

        init()

        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        rvdataPelanggan.layoutManager = layoutManager
        rvdataPelanggan.setHasFixedSize(true)

        pelangganList = arrayListOf()
        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        rvdataPelanggan = findViewById(R.id.rvDATA_PELANGGAN)
        fabDATA_PENGGUNA_Tambah = findViewById(R.id.fabDATA_PENGGUNA_Tambah)
        tvDataKosong = findViewById(R.id.tvDatapelangganKosong) // Pastikan ini ada di layout XML

        fabDATA_PENGGUNA_Tambah.setOnClickListener {
            val intent = Intent(this, TambahPelanggan::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        val query = myRef.orderByChild("idPelanggan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pelangganList.clear()
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val pelanggan = dataSnapshot.getValue(ModelPelanggan::class.java)
                        if (pelanggan != null) {
                            pelangganList.add(pelanggan)
                        }
                    }

                    if (pelangganList.isEmpty()) {
                        tvDataKosong.visibility = View.VISIBLE
                        rvdataPelanggan.visibility = View.GONE
                    } else {
                        tvDataKosong.visibility = View.GONE
                        rvdataPelanggan.visibility = View.VISIBLE

                        val adapter = DataPelangganAdapter(pelangganList)
                        rvdataPelanggan.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }

                } else {
                    // Tidak ada data
                    tvDataKosong.visibility = View.VISIBLE
                    rvdataPelanggan.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPelanggan, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
