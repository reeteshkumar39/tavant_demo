package com.tavant.demo.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tavant.demo.data.model.Product
import com.tavant.demo.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsRepo:ProductRepository): ViewModel() {

    val isAddedFavorite: MutableLiveData<Boolean> = MutableLiveData()

    fun addFavoriteArticle(product: Product) = viewModelScope.launch {
        isAddedFavorite.value =  newsRepo.addProduct(product)
    }
}

