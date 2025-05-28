
package com.aisyah.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelTambahan

class AdapterKonfirmasiTambahan(
    private val list: List<ModelTambahan>
) : RecyclerView.Adapter<AdapterKonfirmasiTambahan.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvID: TextView = view.findViewById(R.id.idKDtambahan)
        val tvNama: TextView = view.findViewById(R.id.tvnamatambahanKD)
        val tvHarga: TextView = view.findViewById(R.id.tvhargatambahanKD)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_konfirmasi_data_tambahan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvID.text = item.idTambahan ?: "-"
        holder.tvNama.text = item.namaTambahan ?: "-"
        holder.tvHarga.text = item.hargaTambahan?.toString() ?: "0"
    }
}

