package com.aisyah.laundry.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import java.text.NumberFormat
import java.util.*
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelLayanan
import com.aisyah.laundry.modeldata.ModelPelanggan
import com.aisyah.laundry.modeldata.ModelTambahan

class DataPilihTambahanAdapter (
    private val listTambahan: ArrayList<ModelTambahan>,
    private val tvKosong: TextView
) : RecyclerView.Adapter<DataPilihTambahanAdapter.ViewHolder>(), Filterable {

    private var filteredList: ArrayList<ModelTambahan> = ArrayList(listTambahan)
    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.cvDatapilihLayanantambahan)
        val tvID: TextView = itemView.findViewById(R.id.idpilihLayanantambahan)
        val tvNama: TextView = itemView.findViewById(R.id.tvnamapilihlayanantambahan)
        val tvHarga: TextView = itemView.findViewById(R.id.tvhargapilihlayanantambahan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.card_data_pilihlayanantambahan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nomor = position + 1
        val item = filteredList[position]
        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
        val hargaFormatted = "Rp ${formatter.format(item.hargaTambahan ?: 0)}"


        holder.tvID.text = "ID: ${item.idTambahan ?: "-"}"
        holder.tvNama.text = item.namaTambahan ?: "-"
        holder.tvHarga.text = hargaFormatted


        holder.cvCard.setOnClickListener {
            val intent = Intent().apply {
                putExtra("idTambahan", item.idTambahan)
                putExtra("nama", item.namaTambahan)
                putExtra("harga", item.hargaTambahan)
            }


            if (context is Activity) {
                (context as Activity).setResult(Activity.RESULT_OK, intent)
                (context as Activity).finish()
            }
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint?.toString()?.lowercase()?.trim() ?: ""
                val resultList = if (keyword.isEmpty()) {
                    listTambahan
                } else {
                    listTambahan.filter {
                        it.namaTambahan?.lowercase()?.contains(keyword) == true
                    }
                }

                return FilterResults().apply {
                    values = ArrayList(resultList)
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as? ArrayList<ModelTambahan> ?: arrayListOf()
                notifyDataSetChanged()

                tvKosong.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }
}
