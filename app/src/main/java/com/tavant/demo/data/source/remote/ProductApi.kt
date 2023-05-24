package com.tavant.demo.data.source.remote

import com.tavant.demo.data.model.Product
import com.tavant.demo.util.constants.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET(Constants.ENDPOINT_HEADLINES)
    suspend fun getProduct(
        @Query("limit")
        limit:Int=1
    ):Response<List<Product>>
}