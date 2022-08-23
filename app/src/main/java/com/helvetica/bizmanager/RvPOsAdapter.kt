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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item_po, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvPOId).text = myList[position].id
        holder.itemView.findViewById<TextView>(R.id.tvPORequestedBy).text = myList[position].requested_by
        holder.itemView.findViewById<TextView>(R.id.tvPODatum).text = myList[position].datum
        holder.itemView.findViewById<TextView>(R.id.tvPOCompany).text = myList[position].company
        holder.itemView.findViewById<TextView>(R.id.tvPOCompanyCode).text = myList[position].company_code
        holder.itemView.findViewById<TextView>(R.id.tvPOShortText).text = myList[position].short_text
        holder.itemView.findViewById<TextView>(R.id.tvPOQuantity).text = myList[position].po_quantity
        holder.itemView.findViewById<TextView>(R.id.tvPOOverallLimit).text = myList[position].overall_limit
        holder.itemView.findViewById<TextView>(R.id.tvPOGRExecutionDate).text = myList[position].gr_execution_date
        holder.itemView.findViewById<TextView>(R.id.tvPOSBU).text = myList[position].sbu
        holder.itemView.findViewById<TextView>(R.id.tvPOStatus).text = myList[position].status
        holder.itemView.findViewById<TextView>(R.id.tvPOInvoice).text = myList[position].invoice
        holder.itemView.findViewById<TextView>(R.id.tvPOGR).text = myList[position].gr
        holder.itemView.findViewById<TextView>(R.id.tvPOManager).text = myList[position].manager
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setPOData(newList: List<PO>) {
        myList = newList
        notifyDataSetChanged()
    }

}