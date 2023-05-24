package com.tavant.demo.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tavant.demo.data.model.Product
/*
 * Crud Operation
 */
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product) : Long

    @Query("SELECT * FROM productTable")
    fun getFavProducts() : LiveData<List<Product>>

    @Query("SELECT title FROM productTable")
    fun getProductTitles():List<String>

    @Delete
    suspend fun deleteProduct(product: Product)
}