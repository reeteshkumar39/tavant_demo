package com.tavant.demo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tavant.demo.data.model.Product
import com.tavant.demo.data.repository.ProductRepository
import com.tavant.demo.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(private val newsRepo:ProductRepository): ViewModel() {

    val getNews: MutableLiveData<Resource<List<Product>>> = MutableLiveData()
    private val pageNumber=10

    fun getBreakingNews() = viewModelScope.launch {
        getNews.postValue(Resource.Loading())
        val getNewsResponse = newsRepo.getProductFromApi(pageNumber)
        getNews.postValue(handleNewsResponse(getNewsResponse))
    }

    private fun handleNewsResponse(response: Resource<List<Product>>) = when(response){
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
    }
}
