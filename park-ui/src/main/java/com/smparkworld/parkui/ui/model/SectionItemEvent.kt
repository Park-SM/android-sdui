package com.smparkworld.parkui.ui.model

sealed class SectionItemEvent {

    data class Click(
        val linkUri: String
    ) : SectionItemEvent()

    data class LongClick(
        val linkUri: String
    ) : SectionItemEvent()

    data class WishClick(
        val id: Long,
        val isWished: Boolean
    ) : SectionItemEvent()
}
