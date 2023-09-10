package com.example.auth_catalog.util.dialog.loading

import androidx.fragment.app.FragmentActivity

/* Define the instances of progress dialog */
private val loadingDialogInstances: ArrayList<LoadingDialogFragment> = arrayListOf()

/** Show the progress dialog. */
fun FragmentActivity.showProgressDialog() {
    val loadingDialogFragment = LoadingDialogFragment.newInstance()
    loadingDialogFragment.show(supportFragmentManager, null)
    loadingDialogInstances.add(loadingDialogFragment)
}

/** Dismiss the progress dialog. */
fun FragmentActivity.dismissProgressDialog() {
    loadingDialogInstances.forEach { it.dismissAllowingStateLoss() }
    loadingDialogInstances.clear()
}
