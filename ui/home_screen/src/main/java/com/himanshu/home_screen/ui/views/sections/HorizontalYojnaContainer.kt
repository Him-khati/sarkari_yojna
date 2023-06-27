package com.himanshu.home_screen.ui.views.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshu.home_screen.ui.views.sections.inner_items.YojnaItemHorizontal
import com.himanshu.sarkari_yojna.android_common_ui.extensions.capitalizeFirstLetter
import com.himanshu.sarkari_yojna.android_common_ui.extensions.showShimmer
import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkari_yojna.theme.spacing
import java.time.LocalDateTime

@Preview
@Composable
fun HorizontalYojnaContainerPreviewWithData() = HorizontalYojnaContainer(
    modifier = Modifier,
    sectionId = "section-id",
    sectionTitle = "Section title",
    yojna = listOf(
        Yojna(
            id = "yojna-id",
            name = "yojna-name",
            artUrl = "https://budgetstockphoto.com/samples/pics/electronic.jpg",
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
    showLoadingShimmer = false
)

@Preview
@Composable
fun HorizontalYojnaContainerPreview() = HorizontalYojnaContainer(
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


@Composable
fun HorizontalYojnaContainer(
    modifier: Modifier,
    sectionId: String,
    sectionTitle: String,
    yojna: List<Yojna>,
    showViewMore: Boolean,
    onViewMoreClicked: (sectionId: String) -> Unit,
    onYojnaClicked: (yojna: Yojna) -> Unit,
    showLoadingShimmer: Boolean = false
) = Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(
            horizontal = MaterialTheme.spacing.screenStartSpacing,
            vertical = MaterialTheme.spacing.screenStartSpacing
        ),
    verticalArrangement = Arrangement.spacedBy(
        MaterialTheme.spacing.screenStartSpacing
    )
) {

    Text(
        modifier = Modifier
            .defaultMinSize(
                minWidth = 140.dp
            )
            .showShimmer(showLoadingShimmer),
        text = sectionTitle.capitalizeFirstLetter(),
        style = MaterialTheme.typography.titleMedium
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            MaterialTheme.spacing.screenStartSpacing
        ),
        userScrollEnabled = !showLoadingShimmer
    ) {

        items(yojna) {
            YojnaItemHorizontal(
                sectionId = sectionId,
                yojna = it,
                onYojnaClicked = onYojnaClicked,
                showLoadingShimmer = showLoadingShimmer
            )
        }
    }
}