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
import com.smparkworld.park.domain.dto.SectionDTO
import com.smparkworld.park.extension.viewModels
import com.smparkworld.park.ui.model.SectionViewBinder
import javax.inject.Inject
import kotlin.reflect.KClass

internal typealias ViewBinderMap = Map<KClass<out SectionDTO>, SectionViewBinder<SectionDTO, RecyclerView.ViewHolder>>

abstract class ParkFragment<V : ViewDataBinding> : Fragment() {

    @Inject
    lateinit var viewBinders: @JvmSuppressWildcards ViewBinderMap

    private val vm: ParkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: V = DataBindingUtil.inflate(inflater, layout, container, false)

        initViewsInternal(getSections())
        initObserversInternal(getSections())

        initViews(binding)
        initObservers(binding)

        return binding.root
    }

    private fun initViewsInternal(sections: RecyclerView) {
        sections.layoutManager = LinearLayoutManager(requireContext())
        sections.adapter = ParkSectionAdapter(viewBinders)
    }

    private fun initObserversInternal(sections: RecyclerView) {
        vm.items.observe(viewLifecycleOwner) { items ->
            (sections.adapter as? ParkSectionAdapter)?.submitList(items)
        }
    }

    open fun initViews(binding: V) {}
    open fun initObservers(binding: V) {}

    abstract fun getSections(): RecyclerView
    abstract val layout: Int
}