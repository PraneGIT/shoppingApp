package com.example.shoppingapp.Repository

import com.example.shoppingapp.models.itemShopping

class productRepository {
    suspend fun getAllProducts() = RetrofitInstance.api.getAllProducts(RequestModel("products","test","Cluster0"))
}