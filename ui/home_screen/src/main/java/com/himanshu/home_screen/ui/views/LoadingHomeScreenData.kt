package com.himanshu.home_screen.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.himanshu.home_screen.ui.views.sections.HorizontalYojnaContainer
import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkari_yojna.theme.spacing
import java.time.LocalDateTime

@Preview
@Composable
fun LoadingHomeScreenDataPreview() {
    LoadingHomeScreenData()
}

@Composable
fun LoadingHomeScreenData() = Column(
    modifier = Modifier.fillMaxSize()
) {

    //TopInformationLayoutShimmer()
    CategoriesLoaderShimmer()
}


@Composable
fun TopInformationLayoutShimmer() {
    Text(
        text = "Content to display after content has loaded",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(MaterialTheme.spacing.screenStartSpacing)
            .placeholder(
                visible = true,
                color = Color.LightGray,
                // optional, defaults to RectangleShape
                shape = MaterialTheme.shapes.medium,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = Color.White
                )
            )
    )


}

@Composable
fun CategoriesLoaderShimmer() = HorizontalYojnaContainer(
    modifier = Modifier,
    sectionId = "section-id",
    sectionTitle = "Section title",
    yojna = listOf(
        Yojna(
            id = "yojna-id",
            name = "yojna-name",
            artUrl = null,
            bookmarked = false,
            lastOpenedOn = null,
            updatedOn = LocalDateTime.now()
        ),
        Yojna(
            id = "yojna-id",
            name = "yojna-name",
            artUrl = null,
            bookmarked = false,
            lastOpenedOn = null,
            updatedOn = LocalDateTime.now()
        )
    ),
    showViewMore = false,
    onViewMoreClicked = {},
    onYojnaClicked = {},
    showLoadingShimmer = true
)


