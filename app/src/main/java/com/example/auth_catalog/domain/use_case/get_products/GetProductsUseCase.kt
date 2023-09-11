package com.example.auth_catalog.domain.use_case.get_products

import com.example.auth_catalog.domain.Repository
import com.example.auth_catalog.util.clean.Either
import com.example.auth_catalog.util.clean.UseCase
import javax.inject.Inject

/**
 * Get products.
 */
class GetProductsUseCase @Inject constructor(
    private val repository: Repository,
) : UseCase<GetProductsResponse, GetProductsParams, GetProductsFailure>() {

    /** Execute use case. */
    override suspend fun run(params: GetProductsParams): Either<GetProductsFailure, GetProductsResponse> =
        repository.getProducts()

}