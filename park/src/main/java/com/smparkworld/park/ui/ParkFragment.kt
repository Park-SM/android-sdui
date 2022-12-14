package com.smparkworld.park.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smparkworld.core.ExtraKey
import com.smparkworld.park.di.annotation.SectionViewBinders
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.extension.viewModels
import com.smparkworld.park.ui.model.SectionViewBinder
import javax.inject.Inject

private typealias ViewBinderMap = Map<String, SectionViewBinder<out SectionDTO, *>>
private typealias ViewBinderMapInternal = Map<String, SectionViewBinder<SectionDTO, RecyclerView.ViewHolder>>

abstract class ParkFragment<V : ViewDataBinding> : Fragment() {

    @Inject
    @SectionViewBinders
    lateinit var viewBinders: @JvmSuppressWildcards ViewBinderMap

    private val vm: ParkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: V = DataBindingUtil.inflate(inflater, layout, container, false)

        onCreateBinding(binding)

        initViewsInternal(getSections(binding))
        initObserversInternal(getSections(binding))
        return binding.root
    }

    fun setRequestUrl(url: String): ParkFragment<V> {
        arguments = (arguments ?: Bundle()).apply {
            putString(ExtraKey.REQUEST_URL, url)
        }
        return this
    }

    @Suppress("UNCHECKED_CAST")
    private fun initViewsInternal(sections: RecyclerView) {
        for ((_, viewBinder) in viewBinders) {
            viewBinder.initialize(this, vm)
        }
        sections.itemAnimator = null
        sections.layoutManager = LinearLayoutManager(requireContext())
        sections.adapter = ParkSectionAdapter(viewBinders as ViewBinderMapInternal)
    }

    private fun initObserversInternal(sections: RecyclerView) {
        vm.items.observe(viewLifecycleOwner) { items ->
            (sections.adapter as? ParkSectionAdapter)?.submitList(items)
        }
    }

    open fun onCreateBinding(binding: V) {}

    abstract fun getSections(binding: V): RecyclerView
    abstract val layout: Int
}