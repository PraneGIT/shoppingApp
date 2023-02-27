package com.example.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.adapters.MainRVadapter
import com.example.shoppingapp.databinding.ActivityCartBinding
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.models.itemShopping

class cartActivity : AppCompatActivity() {
    private var binding: ActivityCartBinding?=null
    private var toolbar_cart: Toolbar?=null
    private var dataList = mutableListOf<itemShopping>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        toolbar_cart=binding?.toolbarCart

        setupActionBar()
        setUpRV()
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_cart)
        toolbar_cart?.title="Shopping Cart"
    }

    private fun setUpRV(){
        //random data
        dataList.add(itemShopping("1","Title1","Desc1",R.drawable.random.toString(),69,true,false))
        dataList.add(itemShopping("2","Title2","Desc2",R.drawable.random.toString(),69,true,false))

        findViewById<RecyclerView>(R.id.rv_cart).layoutManager=
            GridLayoutManager(applicationContext,2)
        var main_adapter= MainRVadapter(this, dataList)
        findViewById<RecyclerView>(R.id.rv_cart).adapter=main_adapter
    }


}