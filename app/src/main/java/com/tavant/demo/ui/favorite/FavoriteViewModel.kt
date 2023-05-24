package com.tavant.demo.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tavant.demo.data.model.Product
import com.tavant.demo.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val newsRepo:ProductRepository): ViewModel() {

    fun addProduct(product: Product) = viewModelScope.launch {
        newsRepo.addProduct(product)
    }

    fun getFavProducts() = newsRepo.getFavProducts()

    fun deleteProduct(product: Product) = viewModelScope.launch {
        newsRepo.deleteProduct(product)
    }
}