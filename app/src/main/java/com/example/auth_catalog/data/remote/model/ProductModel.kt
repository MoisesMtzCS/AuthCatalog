package com.example.auth_catalog.data.remote.model

import com.example.auth_catalog.domain.entity.ProductEntity

data class ProductModel(
    val limit: Int,
    val products: List<ProductEntity>,
    val skip: Int,
    val total: Int
)