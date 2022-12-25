package com.smparkworld.parkui.ui.delegator

import com.smparkworld.core.ui.delegator.WishStatesDelegator
import com.smparkworld.domain.dto.SectionDTO
import javax.inject.Inject

class ParkDefaultDelegators @Inject constructor(
    private val sectionDefaultDelegator: SectionDefaultDelegator,
    private val redirectDefaultDelegator: RedirectDefaultDelegator,
    private val sectionWishDelegator: SectionWishStatesDelegator
) : ParkDelegators {

    override val sectionDelegator: SectionDelegator
        get() = sectionDefaultDelegator

    override val redirectDelegator: RedirectDelegator
        get() = redirectDefaultDelegator

    override val wishDelegator: WishStatesDelegator<SectionDTO>
        get() = sectionWishDelegator
}