package com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items

import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.inner_data.Category
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data.SectionMetaData
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data.SectionScrollDirection
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection

data class CategorySection(
    val sectionMetaData: SectionMetaData,
    val direction: SectionScrollDirection,
    val categories: List<Category>
)
