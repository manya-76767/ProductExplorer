package com.example.productexplorer.data



class ProductRepository(
    private val api: ProductApi
) {

    suspend fun getProducts(): ProductResponse {
        return api.getProducts()
    }
}