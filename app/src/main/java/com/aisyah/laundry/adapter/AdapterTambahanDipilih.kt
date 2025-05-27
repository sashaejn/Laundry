package com.aisyah.laundry.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelTambahan
import java.text.NumberFormat
import java.util.Locale

class AdapterTambahanDipilih(
    private val list: ArrayList<ModelTambahan>
) : RecyclerView.Adapter<AdapterTambahanDipilih.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvID: TextView = view.findViewById(R.id.iditemtambahan)
        val tvNama: TextView = view.findViewById(R.id.tvnamaitemtambahan)
        val tvHarga: TextView = view.findViewById(R.id.tvhargaitemtambahan)
        val btnHapus: ImageView = view.findViewById(R.id.hapuslayanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_item_tambahan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]




        holder.tvID.text = item.idTambahan ?: "-"
        holder.tvNama.text = item.namaTambahan ?: "-"
        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
        val hargaNumber = item.hargaTambahan
            ?.replace(".", "")
            ?.replace(",", "")
            ?.trim()
            ?.toIntOrNull() ?: 0
        val hargaFormatted = "Rp ${formatter.format(hargaNumber)}"

        holder.tvHarga.text = hargaFormatted

        val intent = Intent().apply {
            putExtra("idLayanan", item.idTambahan) // GANTI dari "idPelanggan" ke "idLayanan"
            putExtra("nama", item.namaTambahan)
            putExtra("harga", item.hargaTambahan)
        }


        holder.btnHapus.setOnClickListener {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
    }


}