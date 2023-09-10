package com.example.auth_catalog.util.dialog.loading

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.auth_catalog.R
import com.example.auth_catalog.databinding.FragmentLoadingDialogBinding

/**
 * Loading dialog definition.
 */
class LoadingDialogFragment : DialogFragment() {

    /* ViewBinding instance associated to the current screen */
    private val binding by lazy { FragmentLoadingDialogBinding.inflate(layoutInflater) }

    /** Called at moment that the current screen is created. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    /** Obtains the root view. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    /** Display the dialog, adding the fragment to the given FragmentManager. */
    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {
        }
    }

    companion object {

        /**
         * Obtains a new instance of [LoadingDialogFragment].
         */
        fun newInstance(): LoadingDialogFragment =
            LoadingDialogFragment()

    }

}