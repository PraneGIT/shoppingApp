package com.example.shoppingapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.adapters.MainRVadapter
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.models.itemShopping
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null
    private var toolbar_main: Toolbar?=null
    private var dataList = mutableListOf<itemShopping>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        toolbar_main=findViewById(R.id.toolbar_main)
        setupActionBar()
        binding?.navView?.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_sign_out ->{
                    FirebaseAuth.getInstance().signOut()
                    val intent=Intent(this,SignIn::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                     true
                }
                R.id.nav_purchase_history->{
                    val intent=Intent(this,purchaseHistory::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                     false
                }
            }
        }

        setUpRV()

    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_main)
        toolbar_main?.title="ShoppingApp"
    }

    private fun setUpRV(){
        //random data
        dataList.add(itemShopping("Title1","Desc1",R.drawable.random.toString(),69,true,false))
        dataList.add(itemShopping("Title2","Desc2",R.drawable.random.toString(),69,true,false))
        dataList.add(itemShopping("Title3","Desc3",R.drawable.random.toString(),69,true,false))
        dataList.add(itemShopping("Title3","Desc3",R.drawable.random.toString(),69,true,false))

        findViewById<RecyclerView>(R.id.rv_main).layoutManager=GridLayoutManager(applicationContext,2)
        var main_adapter=MainRVadapter(this, dataList)
        findViewById<RecyclerView>(R.id.rv_main).adapter=main_adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_cart ->{
               startActivity(Intent(this,cartActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }


}