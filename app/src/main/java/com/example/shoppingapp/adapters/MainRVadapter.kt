package com.example.shoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.itemActivity
import com.example.shoppingapp.models.itemShopping

class MainRVadapter(var context: Context,private var list:MutableList<itemShopping>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shopping,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

       val item=list[position]
        if(holder is myViewHolder){
            Glide
                .with(context)
                .load(item.image)
                .centerCrop()
                .placeholder(R.drawable.random)
                .into(holder.itemView.findViewById(R.id.rv_itemImage))

            holder.itemView.findViewById<TextView>(R.id.rv_title).text=item.title
            holder.itemView.findViewById<TextView>(R.id.rv_price).text="₹ ${item.price.toString()}"

            holder.itemView.setOnClickListener {
                var intent= Intent(context,itemActivity::class.java)
                intent.putExtra("item",item)
                context.startActivity(intent)

            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private class myViewHolder(view: View):RecyclerView.ViewHolder(view)
}