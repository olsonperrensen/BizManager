package com.helvetica.bizmanager

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class rvEmployeesAdapter : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater  = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        
    }

    override fun getItemCount(): Int {
        return 5
    }

}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val ivPhoto = view.findViewById<ImageView>(R.id.ivPhoto)
    val tvId = view.findViewById<TextView>(R.id.tvId)
    val tvIdData = view.findViewById<TextView>(R.id.tvIdData)
    val btnEdit = view.findViewById<Button>(R.id.btnEdit)
}