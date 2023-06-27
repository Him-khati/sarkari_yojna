package com.himanshu.home_screen.ui.views.sections.inner_items

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshu.home_screen.R
import com.himanshu.sarkari_yojna.android_common_ui.extensions.showShimmer
import com.himanshu.sarkari_yojna.android_common_ui.views.SyImage
import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import java.time.LocalDateTime

@Preview
@Composable
fun YojnaItemHorizontalPreview() = YojnaItemHorizontal(
    sectionId = "section-id",
    yojna = Yojna(
        id = "yojna-id",
        name = "yojna-name",
        artUrl = null,
        bookmarked = false,
        lastOpenedOn = null,
        updatedOn = LocalDateTime.now()
    ), onYojnaClicked = {},
    showLoadingShimmer = true
)

@Composable
fun YojnaItemHorizontal(
    sectionId: String,
    yojna: Yojna,
    onYojnaClicked: (yojna: Yojna) -> Unit,
    showLoadingShimmer: Boolean = false
) = Column(
    modifier = Modifier
        .clip(
            MaterialTheme.shapes.medium
        )
        .clickable {
            onYojnaClicked.invoke(yojna)
        },
) {
     if (yojna.artUrl != null) {
        SyImage(
            modifier = Modifier
                .width(250.dp)
                .height(180.dp),
            uri = Uri.parse(yojna.artUrl),
            contentDescription = yojna.name,
            onErrorWhileLoadingImage = { data: Any, error: Throwable ->

            },
            showLoadingShimmer = showLoadingShimmer
        )

    } else {
        SyImage(
            modifier = Modifier
                .width(250.dp)
                .height(180.dp),
            image = R.drawable.bg_sample1,
            contentDescription = yojna.name,
            onErrorWhileLoadingImage = { data: Any, error: Throwable ->

            },
            showLoadingShimmer = showLoadingShimmer
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        modifier = Modifier
            .defaultMinSize(
                minWidth = 140.dp
            )
            .showShimmer(showLoadingShimmer),
        text = yojna.name,
        style = MaterialTheme.typography.bodyMedium
    )
}