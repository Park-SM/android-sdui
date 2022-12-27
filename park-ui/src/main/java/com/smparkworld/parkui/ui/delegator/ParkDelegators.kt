package com.smparkworld.parkui.ui.delegator

import com.smparkworld.core.ui.delegator.WishStatesDelegator
import com.smparkworld.domain.dto.SectionDTO

interface ParkDelegators {

    val sectionDelegator: SectionDelegator

    val redirectDelegator: RedirectDelegator

    val wishDelegator: WishStatesDelegator<SectionDTO>
}