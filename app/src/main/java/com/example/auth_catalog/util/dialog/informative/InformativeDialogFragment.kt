package com.example.auth_catalog.util.dialog.informative

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.auth_catalog.R
import com.example.auth_catalog.databinding.FragmentInformativeDialogBinding
import com.example.contacts.feature_tools.dialog.FullWidthDialogFragment

/**
 * Informative dialog definition.
 */
class InformativeDialogFragment : FullWidthDialogFragment() {

    /* ViewBinding instance associated to the current screen */
    private val binding by lazy { FragmentInformativeDialogBinding.inflate(layoutInflater) }

    /* Arguments required for current screen */
    private var message: String? = null

    /** Called at moment that the current screen is created. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        message = arguments?.getString(MESSAGE_KEY)
    }

    /** Obtains the root view. */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    /** Called at moment that the root view is created. */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActions()
        setupViews()
    }

    /** Setup the views information. */
    private fun setupViews() {
        binding.tvMessage.text = message
    }

    /** Setup the actions. */
    private fun setupActions() {
        binding.buttonAccept.setOnClickListener { dismiss() }
    }


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

        /* Key associated to message value */
        private const val MESSAGE_KEY: String = "MESSAGE_KEY"

        /**
         * Obtains a new instance of [InformativeDialogFragment] by [message] value.
         */
        fun newInstance(message: String?): InformativeDialogFragment =
            InformativeDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(MESSAGE_KEY, message)
                }
            }

    }

}