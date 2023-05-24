package com.tavant.demo.data.repository

import android.util.Log
import com.tavant.demo.data.model.Product
import com.tavant.demo.data.source.local.ProductDao
import com.tavant.demo.data.source.remote.ProductApi
import com.tavant.demo.util.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(private val database: ProductDao, private val productApi: ProductApi) {

    suspend fun getProductFromApi(pageNumber:Int):Resource<List<Product>>{

       try {
           val articleTitles:List<String>
           withContext(Dispatchers.IO){
               articleTitles = database.getProductTitles()
           }
           val breakingNewList = mutableListOf<Product>()

           productApi.getProduct(pageNumber).body()?.map {
               Log.e("data-------------->",it.toString())

               breakingNewList.add(
                   Product(
                       id = it.id,
                       title = it.title,
                       price=it.price,
                       category=it.category,
                       description = it.description,
                       image = it.image,
                       isFav = articleTitles.contains(it.title)
                   )
               )
           }

           return Resource.Success(breakingNewList)
       }
       catch (e:Exception){
           return Resource.Error(e.message.orEmpty())
       }
    }

    suspend fun addProduct(product: Product):Boolean {
        val articleTitles: List<String>
        withContext(Dispatchers.IO){
            articleTitles = database.getProductTitles()
        }
        return if(!articleTitles.contains(product.title)){
            database.addProduct(product)
            true
        }
        else{
            false
        }
    }

    fun getFavProducts() = database.getFavProducts()

    suspend fun deleteProduct(product: Product) =  database.deleteProduct(product)
}