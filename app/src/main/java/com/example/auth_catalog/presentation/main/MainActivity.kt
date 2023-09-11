package com.example.auth_catalog.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.auth_catalog.R
import com.example.auth_catalog.util.clean.Status
import com.example.auth_catalog.databinding.ActivityMainBinding
import com.example.auth_catalog.domain.entity.ProductEntity
import com.example.auth_catalog.domain.use_case.get_products.GetProductsFailure
import com.example.auth_catalog.domain.use_case.get_products.GetProductsResponse
import com.example.auth_catalog.domain.use_case.get_products.GetProductsStatus
import com.example.auth_catalog.presentation.login.LoginActivity
import com.example.auth_catalog.presentation.main.adapter.ProductAdapter
import com.example.auth_catalog.util.dialog.informative.showInformativeDialog
import com.example.auth_catalog.util.dialog.loading.dismissProgressDialog
import com.example.auth_catalog.util.dialog.loading.showProgressDialog
import com.example.auth_catalog.util.flow.launchAndRepeatOnLifecycle
import com.example.auth_catalog.util.flow.observeFor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* Android resources */
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    /*****************************************************************************************
     * LIFECYCLE
     ****************************************************************************************/

    /** Called at create the screen. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
    }

    /*****************************************************************************************
     * VIEWS - SETUP
     ****************************************************************************************/

    /** Setup the views data. */
    private fun setupViews() {
        launchGetProductsFlow()
        setupPullToRefresh()
    }

    /** Setup pull to refresh */
    private fun setupPullToRefresh() {
        binding.srlRefresh.setOnRefreshListener {
            launchGetProductsFlow()
        }
    }

    /** Setup the recycler view contacts. */
    private fun fillRecyclerView(products: List<ProductEntity>) {
        val productAdapter = ProductAdapter(products)
        binding.rvProduct.adapter = productAdapter
        binding.rvProduct.layoutManager = LinearLayoutManager(this)
    }

    /** Navigate to login. */
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    /*****************************************************************************************
     * USE CASE - GET PRODUCTS
     ****************************************************************************************/

    /** Launch the get products flow.*/
    private fun launchGetProductsFlow() {
        launchAndRepeatOnLifecycle {
            val response = viewModel.launchGetProducts()
            observeFor(response, ::getProductsCollector)
        }
    }

    /** Collect the [status] for get products flow. */
    private fun getProductsCollector(status: GetProductsStatus) {
        binding.srlRefresh.isRefreshing = false
        when (status) {
            is Status.Loading -> showProgressDialog()
            is Status.Failed -> manageGetProductsFailure(status.failure)
            is Status.Done -> manageGetProductsResponse(status.value)
        }
    }

    /** Manage the get products [response] instance. */
    private fun manageGetProductsResponse(response: GetProductsResponse) {
        dismissProgressDialog()
        fillRecyclerView(response.products)
    }

    /** Manage the get products [failure] instance. */
    private fun manageGetProductsFailure(failure: GetProductsFailure) {
        dismissProgressDialog()
        binding.srlRefresh.isRefreshing = false
        when (failure) {
            GetProductsFailure.TokenExpiredFailure ->
                showInformativeDialog(
                    message = getString(R.string.token_expired_failure),
                    action = { navigateToLogin() }
                )

            GetProductsFailure.GenericFailure ->
                showInformativeDialog(getString(R.string.login_network_connection_failure))

            GetProductsFailure.NetworkConnectionFailure ->
                showInformativeDialog(getString(R.string.login_generic_failure))
        }
    }

}