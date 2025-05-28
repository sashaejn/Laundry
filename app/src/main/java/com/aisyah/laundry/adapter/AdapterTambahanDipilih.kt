package com.aisyah.laundry.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelTambahan

class AdapterTambahanDipilih(
    private val list: MutableList<ModelTambahan>
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

        holder.tvID.text = "[${position + 1}]"

        holder.tvNama.text = item.namaTambahan ?: "-"
        holder.tvHarga.text = item.hargaTambahan?.toString() ?: "0"

        holder.btnHapus.setOnClickListener {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
    }
}
