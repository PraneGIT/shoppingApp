package com.example.shoppingapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.Repository.RequestModel
import com.example.shoppingapp.Repository.RetrofitInstance
import com.example.shoppingapp.Repository.productRepository
import com.example.shoppingapp.adapters.MainRVadapter
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.models.itemShopping
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

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

       getProducts()

        //setUpRV()


    }

    private fun setupActionBar(){
        setSupportActionBar(toolbar_main)
        toolbar_main?.title="ShoppingApp"
    }

    private fun setUpRV(){
        //random data
//        dataList.add(itemShopping("1","Title1","Desc1",R.drawable.random.toString(),69,true,false))
//        dataList.add(itemShopping("2","Title2","Desc2",R.drawable.random.toString(),69,true,false))
//        dataList.add(itemShopping("3","Title3","Desc3",R.drawable.random.toString(),69,true,false))
//        dataList.add(itemShopping("4","Title3","Desc3",R.drawable.random.toString(),69,true,false))

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

    fun getProducts()= GlobalScope.launch{
        val response=RetrofitInstance.api.getAllProducts(RequestModel("products","test","Cluster0"))
        e("response",response.body()?.documents.toString())

        for( i in response.body()?.documents!!){
            dataList.add(i)
        }
        runOnUiThread(Runnable {
            setUpRV()
        })

    }


}