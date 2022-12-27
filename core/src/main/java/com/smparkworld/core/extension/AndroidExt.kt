package com.smparkworld.core.extension

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified VM : ViewModel> Fragment.viewModels(
    noinline getFactory: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val getNonNullFactory = getFactory ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(VM::class.java, { this }, getNonNullFactory)
}

inline fun <reified VM : ViewModel> Fragment.parentViewModels(
    noinline getFactory: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val getNonNullFactory = getFactory ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(VM::class.java, ::requireParentFragment, getNonNullFactory)
}

class ViewModelLazy<VM : ViewModel>(
    private val viewModelClass: Class<VM>,
    private val getOwner: () -> ViewModelStoreOwner,
    private val getFactory: () -> ViewModelProvider.Factory
) : Lazy<VM> {

    private var cached: VM? = null

    override val value: VM
        get() {
            val vm = cached
            return if (vm == null) {
                val owner = getOwner.invoke()
                val viewModelFactory = getFactory.invoke()

                ViewModelProvider(owner, viewModelFactory)[viewModelClass].also {
                    cached = it
                }
            } else {
                vm
            }
        }

    override fun isInitialized(): Boolean = (cached != null)
}

val Uri.hostAndPath: String
    get() = "/${host}${path}"