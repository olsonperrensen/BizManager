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
    val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
    val tvUsernameData = view.findViewById<TextView>(R.id.tvUsernameData)
    val tvPwd = view.findViewById<TextView>(R.id.tvPwd)
    val tvPwdData = view.findViewById<TextView>(R.id.tvPwdData)
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvNameData = view.findViewById<TextView>(R.id.tvNameData)
    val tvSbu = view.findViewById<TextView>(R.id.tvSbu)
    val tvSbuData = view.findViewById<TextView>(R.id.tvSbuData)
    val tvLand = view.findViewById<TextView>(R.id.tvLand)
    val tvLandData = view.findViewById<TextView>(R.id.tvLandData)
    val tvManager = view.findViewById<TextView>(R.id.tvManager)
    val tvManagerData = view.findViewById<TextView>(R.id.tvManagerData)
    val btnEdit = view.findViewById<Button>(R.id.btnEdit)
    val btnDelete = view.findViewById<Button>(R.id.btnDelete)
}