package com.helvetica.bizmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.helvetica.bizmanager.model.PO
import com.helvetica.bizmanager.model.Worker

class RvPOsAdapter() : RecyclerView.Adapter<RvPOsAdapter.MyViewHolder>() {
    private var myList = emptyList<PO>()

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvPODetails = view.findViewById<TextView>(R.id.tvPODetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_po, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvPODetails).text =
            myList[position].requested_by
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setPOData(newList: List<PO>) {
        myList = newList
        notifyDataSetChanged()
    }

}