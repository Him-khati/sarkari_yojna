package com.himanshu.sarkariyojna.data.home_screen.models

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data.SectionMetaData

sealed interface HomeScreenSection{

    data class UpdatePreferenceRequest(
        val title : String,
        val subTitle : String
    ) : HomeScreenSection


    data class HorizontalYojnaSection(
        val sectionMetaData: SectionMetaData,
        val yojnaList : List<Yojna>,
        val showViewMore : Boolean
    ) : HomeScreenSection

    data class VerticalYojnaSection(
        val sectionMetaData: SectionMetaData,
        val yojnaList : List<Yojna>,
        val showViewMore : Boolean
    ) : HomeScreenSection


}