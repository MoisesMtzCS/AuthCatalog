package com.example.auth_catalog.util.dialog.informative

import androidx.fragment.app.FragmentActivity

/* Define the instances of informative dialog */
private val informativeDialogInstances: ArrayList<InformativeDialogFragment> = arrayListOf()

/** Show the informative dialog with [message]. */
fun FragmentActivity.showInformativeDialog(message: String? = null, action: (() -> Unit)? = null) {
    val informativeDialogFragment = InformativeDialogFragment.newInstance(message, action   )
    informativeDialogFragment.show(supportFragmentManager, null)
    informativeDialogInstances.add(informativeDialogFragment)
}
