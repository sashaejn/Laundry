package com.aisyah.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aisyah.laundry.R
import com.aisyah.laundry.modeldata.ModelPelanggan

class DataPelangganAdapter (
    private val listpelanggan: ArrayList<ModelPelanggan>) :
    RecyclerView.Adapter<DataPelangganAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pelanggan, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder,position:Int){
        val pelanggan = listpelanggan[position]
        holder.tvID.text = pelanggan.idPelanggan
        holder.tvNama.text = pelanggan.namaPelanggan
        holder.tvAlamat.text = pelanggan.alamatPelanggan
        holder.tvNoHp.text = pelanggan.noHPPelanggan

        holder.btpelangganhub.setOnClickListener{

        }
        holder.btpelangganlihat.setOnClickListener{

        }

    }

    override fun getItemCount(): Int {
        return listpelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvID  : TextView = itemView.findViewById(R.id.idpelanggan)
        val tvNama  : TextView = itemView.findViewById(R.id.tvNama_pelanggan)
        val tvAlamat  : TextView = itemView.findViewById(R.id.tvAlamat_pelanggan)
        val tvNoHp : TextView = itemView.findViewById(R.id.tvNoHp_Pelanggan)
        val btpelangganhub : Button = itemView.findViewById(R.id.btpelangganhub)
        val btpelangganlihat : Button = itemView.findViewById(R.id.btpelangganlihat)
    }
}
