package com.example.auth_catalog.domain.use_case.get_products

import com.example.auth_catalog.util.clean.Status

/**
 * Type alias definition for get products flow.
 */
typealias GetProductsStatus = Status<GetProductsFailure, GetProductsResponse>