package com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items

import com.himanshu.sarkariyojna.data.home_screen.models.YojnaSectionType
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.inner_data.YojnaItem
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data.SectionMetaData
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data.SectionScrollDirection
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection

data class YojnaSection(
    val section : YojnaSectionType,
    val sectionMetaData: SectionMetaData,
    val direction: SectionScrollDirection,
    val yojnaList: List<YojnaItem>
)
