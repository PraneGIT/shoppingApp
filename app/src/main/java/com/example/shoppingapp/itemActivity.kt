package com.example.shoppingapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.graphics.Color
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.shoppingapp.databinding.ActivityItemBinding
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.models.itemShopping

class itemActivity : AppCompatActivity() {
    private var binding: ActivityItemBinding?=null
    private var toolbar_item: Toolbar?=null
    private var item:itemShopping ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        toolbar_item=findViewById(R.id.toolbar_item)
        if(intent.hasExtra("item")){
            item=intent.getParcelableExtra("item")
        }
        item?.let {
            setupUI(it)
        }


        setupActionBar()
    }
    private fun setupActionBar(){
        setSupportActionBar(toolbar_item)
        toolbar_item?.title="Shopping App"
    }

    private fun setupUI(item:itemShopping){

        Glide
            .with(this)
            .load(item.image)
            .centerCrop()
            .placeholder(R.drawable.random)
            .into(findViewById(R.id.iv_itemImage))

        binding?.tvItemTitle?.text=item.title
        binding?.tvItemPrice?.text=item.price.toString()
        binding?.tvItemDesc?.text=item.desc
        if(item.inCart){
            binding?.idFABAdd?.visibility=View.INVISIBLE
        }
        if(!item.inStock){
            binding?.tvInStockItem?.text="not in stock"
            binding?.tvInStockItem?.setTextColor(Color.parseColor("#FF8A80"))

        }
    }
}