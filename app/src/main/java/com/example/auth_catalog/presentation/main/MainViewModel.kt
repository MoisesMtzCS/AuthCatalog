package com.example.auth_catalog.presentation.main

import androidx.lifecycle.ViewModel
import com.example.auth_catalog.domain.use_case.get_products.GetProductsParams
import com.example.auth_catalog.domain.use_case.get_products.GetProductsStatus
import com.example.auth_catalog.domain.use_case.get_products.GetProductsUseCase
import com.example.auth_catalog.domain.use_case.login.LoginParams
import com.example.auth_catalog.util.clean.Status
import com.example.auth_catalog.util.clean.onLeft
import com.example.auth_catalog.util.clean.onRight
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    /*****************************************************************************************
     * USE CASE - GET PRODUCTS
     ****************************************************************************************/

    /** Launch get products.*/
    fun launchGetProducts() = flow<GetProductsStatus> {
        emit(Status.Loading())
        val params = GetProductsParams()
        getProductsUseCase.run(params)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }

}