package com.tavant.demo.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "productTable")
data class Product(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,


    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "price")
    val price: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "category")
    val category: String?,


    @ColumnInfo(name = "image")
    val image: String?,

    val isFav: Boolean = false
) : Parcelable