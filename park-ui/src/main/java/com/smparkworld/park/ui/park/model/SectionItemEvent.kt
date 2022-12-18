package com.smparkworld.park.ui.park.model

import com.smparkworld.park.model.action.ClickableDTO
import com.smparkworld.park.model.action.LongClickableDTO
import com.smparkworld.park.model.action.WishClickableDTO

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
