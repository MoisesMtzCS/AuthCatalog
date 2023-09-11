package com.example.auth_catalog.domain.use_case.get_products

import com.example.auth_catalog.domain.entity.ProductEntity

/** Data response to get products flow. */
data class GetProductsResponse(
    val products: List<ProductEntity>
)