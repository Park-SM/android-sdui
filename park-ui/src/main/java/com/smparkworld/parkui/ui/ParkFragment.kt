package com.smparkworld.parkui.ui

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
import com.smparkworld.core.deeplink.AppUriHandler
import com.smparkworld.core.extension.viewModels
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.parkui.common.pagination.ScrollingViewPaginator
import com.smparkworld.parkui.di.SectionViewBinders
import com.smparkworld.parkui.ui.model.SectionViewBinder
import javax.inject.Inject

private typealias ViewBinderMap = Map<String, SectionViewBinder<out SectionDTO, *>>
private typealias ViewBinderMapInternal = Map<String, SectionViewBinder<SectionDTO, RecyclerView.ViewHolder>>

abstract class ParkFragment<V : ViewDataBinding> : Fragment() {

    @Inject
    @SectionViewBinders
    lateinit var viewBinders: @JvmSuppressWildcards ViewBinderMap

    @Inject
    lateinit var appUriHandler: AppUriHandler

    private lateinit var paginator: ScrollingViewPaginator

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

    override fun onResume() {
        super.onResume()
        vm.onRefreshItems()
    }

    fun setRequestUri(uri: String): ParkFragment<V> {
        arguments = (arguments ?: Bundle()).apply {
            putString(ExtraKey.REQUEST_URI, uri)
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

        paginator = ScrollingViewPaginator.with(sections)
            .setOnNextPageListener {
                vm.onRequestNextSections()
            }
            .create()
    }

    private fun initObserversInternal(sections: RecyclerView) {
        vm.items.observe(viewLifecycleOwner) { items ->
            (sections.adapter as? ParkSectionAdapter)?.submitList(items)
        }
        vm.nextPageTriggerPosition.observe(viewLifecycleOwner) { position ->
            paginator.setNextPageTriggerPosition(position)
        }
        vm.redirectUri.observe(viewLifecycleOwner) { redirectUri ->
            appUriHandler.handle(requireActivity(), redirectUri)
        }
    }

    open fun onCreateBinding(binding: V) {}

    abstract fun getSections(binding: V): RecyclerView
    abstract val layout: Int
}