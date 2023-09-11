package com.example.auth_catalog.domain.use_case.get_products

import com.example.auth_catalog.util.clean.Failure

/** Possible failures at get products. */
sealed class GetProductsFailure : Failure() {

    /** Internet connection failure. */
    data object NetworkConnectionFailure : GetProductsFailure()

    /** Generic Failure*/
    data object GenericFailure : GetProductsFailure()

    /** Failure for Authentication. */
    data object TokenExpiredFailure : GetProductsFailure()

}