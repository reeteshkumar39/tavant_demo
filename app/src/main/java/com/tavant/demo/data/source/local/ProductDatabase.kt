package com.tavant.demo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tavant.demo.data.model.Product

@Database(entities = [Product::class], version = 4, exportSchema = false)
@TypeConverters(TypeConvertor::class)
abstract class ProductDatabase:RoomDatabase() {

    abstract fun getItemFromDao() : ProductDao
}