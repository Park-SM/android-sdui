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
import com.smparkworld.core.ui.support.pagination.ScrollingViewPaginator
import com.smparkworld.domain.dto.SectionDTO
import com.smparkworld.parkui.di.SectionViewBinders
import com.smparkworld.parkui.ui.model.SectionViewBinder
import javax.inject.Inject

private typealias ViewBinderMap = Map<String, SectionViewBinder<out SectionDTO, *>>
private typealias ViewBinderMapInternal = Map<String, SectionViewBinder<SectionDTO, RecyclerView.ViewHolder>>

@Suppress("UNCHECKED_CAST")
abstract class ParkFragment<V : ViewDataBinding, VM: ParkViewModel> : Fragment() {

    @Inject
    @SectionViewBinders
    lateinit var viewBinders: @JvmSuppressWildcards ViewBinderMap

    @Inject
    lateinit var appUriHandler: AppUriHandler

    private lateinit var paginator: ScrollingViewPaginator

    private lateinit var adapter: ParkSectionAdapter

    abstract val vm: VM

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
        vm.onRefreshSections()
    }

    fun setRequestUri(uri: String): ParkFragment<V, VM> {
        arguments = (arguments ?: Bundle()).apply {
            putString(ExtraKey.REQUEST_URI, uri)
        }
        return this
    }

    private fun initViewsInternal(sections: RecyclerView) {
        for ((_, viewBinder) in viewBinders) {
            viewBinder.initialize(this, vm)
        }
        adapter = ParkSectionAdapter(viewBinders as ViewBinderMapInternal)

        sections.itemAnimator = ParkItemAnimator()
        sections.layoutManager = LinearLayoutManager(requireContext())
        sections.adapter = adapter.withBottomLoadState(
            ParkSectionBottomLoadStateAdapter(onRetry = vm::onRequestNextSections)
        )

        paginator = ScrollingViewPaginator.with(sections)
            .setOnNextPageListener {
                vm.onRequestNextSections()
            }
            .create()
    }

    private fun initObserversInternal(sections: RecyclerView) {
        vm.items.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
        }
        vm.nextPageTriggerPosition.observe(viewLifecycleOwner) { position ->
            paginator.setNextPageTriggerPosition(position)
        }
        vm.redirectUri.observe(viewLifecycleOwner) { redirectUri ->
            appUriHandler.handle(requireActivity(), redirectUri)
        }
        vm.bottomLoadState.observe(viewLifecycleOwner) { loadState ->
            adapter.setBottomLoadState(loadState)
        }
    }

    open fun onCreateBinding(binding: V) {}

    abstract fun getSections(binding: V): RecyclerView
    abstract val layout: Int
}