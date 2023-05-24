package com.tavant.demo.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @ColumnInfo(name ="name" )
    val name: String?
):Parcelable