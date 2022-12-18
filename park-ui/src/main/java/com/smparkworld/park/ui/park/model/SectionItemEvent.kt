package com.smparkworld.park.ui.park.model

import com.smparkworld.domain.dto.action.ClickableDTO
import com.smparkworld.domain.dto.action.LongClickableDTO
import com.smparkworld.domain.dto.action.WishClickableDTO

sealed class SectionItemEvent {

    data class Click(
        val model: ClickableDTO
    ) : SectionItemEvent()

    data class LongClick(
        val model: LongClickableDTO
    ) : SectionItemEvent()

    data class WishClick(
        val model: WishClickableDTO,
        val isWished: Boolean
    ) : SectionItemEvent()
}
