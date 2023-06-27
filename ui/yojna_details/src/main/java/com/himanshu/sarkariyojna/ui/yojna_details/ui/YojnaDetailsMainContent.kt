package com.himanshu.sarkariyojna.ui.yojna_details.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.himanshu.sarkari_yojna.android_common_ui.views.SyImage
import com.himanshu.sarkari_yojna.android_common_ui.views.SyToolbar
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkari_yojna.theme.spacing
import com.himanshu.sarkariyojna.android_base.language.Language
import org.jetbrains.annotations.Async

@Preview
@Composable
fun YojnaDetailsMainContentPreview() = YojnaDetailsMainContent(
    yojnaMetaData = YojnaMetaData(
        languageAvailable = listOf(
            Language.English,
            Language.Hindi
        )
    ),
    yojnaDetails = YojnaDetails(
        yojnaId = "id",
        category = "Some Category",
        title = "Yojan Title that can long",
        coverImage = Uri.parse("https://images.unsplash.com/photo-1579353977828-2a4eab540b9a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=774&q=80"),
        author = "Himanshu",
        content = "Yo Content",
        bookmarked = true
    ),
    bionicReadingEnabled = false,
    onBookmarkClicked = {},
    onSelectedLanguageChanged = {},
    pageScrollProgressIndicator = {}
)

@Composable
fun YojnaDetailsMainContent(
    yojnaMetaData: YojnaMetaData,
    yojnaDetails: YojnaDetails,
    bionicReadingEnabled: Boolean,
    onBookmarkClicked: () -> Unit,
    onSelectedLanguageChanged: (Language) -> Unit,
    pageScrollProgressIndicator: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SyToolbar(
                text = "",
                onNavigationBackClick = {

                }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.screenStartSpacing)
                .verticalScroll(rememberScrollState())
        ) {

//            AsyncImage(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(350.dp)
//                    .clip(RoundedCornerShape(30.dp)),
//                model = "https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_1280.jpg",
//                contentDescription = null,
//                contentScale = ContentScale.FillBounds
//            )

            SyImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(30.dp)),
                uri = Uri.parse("https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_1280.jpg"),
                contentDescription = "yojna-cover-image",
                contentScale = ContentScale.FillBounds
            )

            YojnaBriefDetails(
                yojnaMetaData, yojnaDetails, onBookmarkClicked, onSelectedLanguageChanged
            )
        }
    }
}

private fun prepareTextBelowTitle(
    yojnaDetails: YojnaDetails,
    yojnaMetaData: YojnaMetaData
) = buildString {

}

@Composable
fun YojnaBriefDetails(
    yojnaMetaData: YojnaMetaData,
    yojnaDetails: YojnaDetails,
    onBookmarkClicked: () -> Unit,
    onSelectedLanguageChanged: (Language) -> Unit
) {

    Text(
        text = yojnaDetails.title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 2
    )

    Text(
        text = prepareTextBelowTitle(
            yojnaDetails,
            yojnaMetaData
        ), style = MaterialTheme.typography.labelMedium
    )

    Spacer(modifier = Modifier.size(MaterialTheme.spacing.screenStartSpacing))

    Text(
        modifier = Modifier
            .width(30.dp)
            .background(color = MaterialTheme.colorScheme.onBackground)
            .clip(MaterialTheme.shapes.large),
        text = "",
    )
}