package com.helvetica.bizmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.helvetica.bizmanager.model.Worker
import com.helvetica.bizmanager.model.WorkerImg

class RvEmployeesAdapter() : RecyclerView.Adapter<RvEmployeesAdapter.MyViewHolder>() {
    private var myList = emptyList<Worker>()
    private var myListImg = emptyList<WorkerImg>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvNaamData).text = myList[position].naam
        holder.bind(myListImg[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Worker>) {
        myList = newList
        notifyDataSetChanged()
    }

    fun setDataImg(newListImg: List<WorkerImg>) {
        myListImg = newListImg
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto = view.findViewById<ImageView>(R.id.ivPhoto)
        val tvNaam = view.findViewById<TextView>(R.id.tvNaam)
        val tvNaamData = view.findViewById<TextView>(R.id.tvNaamData)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
        fun bind(imgURL: WorkerImg) {
            Glide.with(view.context).load(
                imgURL.url
            ).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.user)
                .into(ivPhoto)
        }
    }
}
