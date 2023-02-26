package com.example.shoppingapp.Repository

import com.example.shoppingapp.Constants
import com.example.shoppingapp.models.itemShopping
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface productAPI {
    @GET("/")
    suspend fun getAllProducts(
        @Query("Content-Type")
        type:String="application/json",
        @Query("Access-Control-Request-Headers")
        ans:String="*",
        @Query("api-key")
        apiKey:String=Constants.API_KEY
    ):Response<itemShopping>

}