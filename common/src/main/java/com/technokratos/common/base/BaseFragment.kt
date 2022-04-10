package com.technokratos.common.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.flowWithLifecycle
import com.technokratos.common.R
import com.technokratos.common.utils.EventObserver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// TODO (fix ktlint on all files)

abstract class BaseFragment<T : BaseViewModel> : Fragment() {

    @Inject
    protected open lateinit var viewModel: T

    private val observables = mutableListOf<LiveData<*>>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
        initViews()
        subscribe(viewModel)

        observe(viewModel.errorLiveData, EventObserver {
            showError(it)
        })

        observe(viewModel.errorWithTitleLiveData, EventObserver {
            showErrorWithTitle(it.first, it.second)
        })

        observe(viewModel.errorFromResourceLiveData, EventObserver {
            showErrorFromResponse(it)
        })
    }

    protected fun showError(errorMessage: String) {
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.common_error_general_title)
            .setMessage(errorMessage)
            .setPositiveButton(R.string.common_ok) { _, _ -> }
            .show()
    }

    protected fun showErrorFromResponse(resId: Int) {
        AlertDialog.Builder(requireActivity())
            .setTitle(R.string.common_error_general_title)
            .setMessage(resId)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .show()
    }

    protected fun showErrorWithTitle(title: String, errorMessage: String) {
        AlertDialog.Builder(requireActivity())
            .setTitle(title)
            .setMessage(errorMessage)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .show()
    }

    override fun onDestroyView() {
        observables.forEach { it.removeObservers(this) }
        super.onDestroyView()
    }

    @Suppress("unchecked_cast")
    protected fun <V : Any?> observe(source: LiveData<V>, observer: Observer<V>) {
        source.observe(viewLifecycleOwner, observer as Observer<in Any?>)
        observables.add(source)
    }

    abstract fun inject()

    abstract fun initViews()

    abstract fun subscribe(viewModel: T)

    fun <T> Flow<T>.observe(lifecycleOwner: LifecycleOwner, action: suspend (value: T) -> Unit) {
        onEach(action)
            .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .launchIn(lifecycleOwner.lifecycle.coroutineScope)
    }
}