package com.aisyah.laundry.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelPelanggan
import com.aisyah.laundry.pelanggan.SuntingPelanggan // Ganti ke activity pelanggan

class DataPelangganAdapter(
    private val listPelanggan: ArrayList<ModelPelanggan>
) : RecyclerView.Adapter<DataPelangganAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelanggan = listPelanggan[position]

        holder.tvID.text = "[${position + 1}]"
        holder.tvNama.text = pelanggan.namaPelanggan
        holder.tvAlamat.text = pelanggan.alamatPelanggan
        holder.tvNoHp.text = pelanggan.noHPPelanggan

        // Klik CardView untuk sunting
        holder.itemView.findViewById<CardView>(R.id.cvDataPelanggan).setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SuntingPelanggan::class.java)
            intent.putExtra("idPelanggan", pelanggan.idPelanggan)
            intent.putExtra("namaPelanggan", pelanggan.namaPelanggan)
            intent.putExtra("alamatPelanggan", pelanggan.alamatPelanggan)
            intent.putExtra("noHPPelanggan", pelanggan.noHPPelanggan)
            context.startActivity(intent)
        }

        holder.btpelangganhub.setOnClickListener {
            // Fitur hubungi bisa ditambahkan di sini
        }

        holder.btpelangganlihat.setOnClickListener {
            // Fitur lihat detail bisa ditambahkan di sini
        }
    }

    override fun getItemCount(): Int {
        return listPelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.idpelanggan)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama_pelanggan)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat_pelanggan)
        val tvNoHp: TextView = itemView.findViewById(R.id.tvNoHp_Pelanggan)
        val btpelangganhub: Button = itemView.findViewById(R.id.btpelangganhub)
        val btpelangganlihat: Button = itemView.findViewById(R.id.btpelangganlihat)
    }
}
