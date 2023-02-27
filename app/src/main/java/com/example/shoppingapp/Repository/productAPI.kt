package com.example.shoppingapp.Repository

import com.example.shoppingapp.Constants
import com.example.shoppingapp.hh
import com.example.shoppingapp.models.itemShopping
import retrofit2.Response
import retrofit2.http.*

interface productAPI {
    @Headers(
        "Content-Type: application/json",
        "Access-Control-Request-Headers: *",
        "api-key: ${Constants.API_KEY}"
    )
    @POST(Constants.BASE_URL)
    suspend fun getAllProducts(
        @Body requestModel: RequestModel
    ):Response<hh>

}