package com.tavant.demo.di

import android.content.Context
import androidx.room.Room
import com.tavant.demo.data.source.local.ProductDatabase
import com.tavant.demo.data.source.local.ProductDao
import com.tavant.demo.data.source.remote.ProductApi
import com.tavant.demo.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance():Retrofit {
       return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit:Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(@ApplicationContext context: Context): ProductDatabase =
        Room.databaseBuilder(context, ProductDatabase::class.java,"articleDatabase")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideArticleDao(articleDb: ProductDatabase): ProductDao {
        return articleDb.getItemFromDao()
    }
}