package com.example.auth_catalog.util.flow

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/** */
fun FragmentActivity.launchAndRepeatOnLifecycle(
    lifecycleCoroutineScope: LifecycleCoroutineScope = lifecycleScope,
    state: Lifecycle.State = Lifecycle.State.CREATED,
    block: CoroutineScope.() -> Unit,
) : Job = lifecycleCoroutineScope.launch {
    repeatOnLifecycle(state, block)
}