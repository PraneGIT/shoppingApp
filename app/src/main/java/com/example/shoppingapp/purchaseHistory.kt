package com.example.shoppingapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.adapters.HistoryRVadapter
import com.example.shoppingapp.databinding.ActivityCartBinding
import com.example.shoppingapp.databinding.ActivityPurchaseHistoryBinding
import com.example.shoppingapp.models.itemHistory
import com.example.shoppingapp.models.itemShopping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class purchaseHistory : AppCompatActivity() {
    private var binding: ActivityPurchaseHistoryBinding?=null
    private var toolbar_history: Toolbar?=null
    private var dataList = mutableListOf<itemHistory>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPurchaseHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        toolbar_history=binding?.toolbarHistory

        setupActionBar()
        setUpRV()
    }

    private fun setUpRV() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dataList.add(itemHistory("Title1","Desc1",R.drawable.random.toString(),69,true,LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString() ))
            dataList.add(itemHistory("Title2","Desc2",R.drawable.random.toString(),69,true,LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString() ))
        }

        binding?.rvHistory?.layoutManager=LinearLayoutManager(this)
        binding?.rvHistory?.adapter=HistoryRVadapter(this,dataList)
    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_history)
        toolbar_history?.title="Shopping App"
    }
}