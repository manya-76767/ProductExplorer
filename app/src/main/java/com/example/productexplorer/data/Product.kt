package com.example.productexplorer.data

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val thumbnail: String,
    val rating: Double
)