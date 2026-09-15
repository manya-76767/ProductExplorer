package com.example.productexplorer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productexplorer.data.Product
import com.example.productexplorer.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error

    init {
        getProducts()
    }
    fun searchProducts(query: String) {
        _searchQuery.value = query
    }

    private fun getProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = false

            try {
                val response = repository.getProducts()
                _products.value = response.products
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = true
            }

            _isLoading.value = false
        }
    }
}