package com.himanshu.sarkari_yojna.android_common_ui.views

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.himanshu.sarkari_yojna.android_common_ui.extensions.showShimmer

@Composable
fun SyImage(
    modifier: Modifier,
    uri: Uri,
    contentDescription: String,
    onErrorWhileLoadingImage: ((data: Any, e: Throwable) -> Unit)? = null,
    showLoadingShimmer: Boolean = false,
    contentScale : ContentScale = ContentScale.Inside
) {
    val request = ImageRequest.Builder(
        LocalContext.current
    ).data(
        uri
    ).crossfade(
        true
    ).scale(
        Scale.FILL
    ).build()

    RealSyImage(
        modifier = modifier,
        imageRequest = request,
        contentDescription = contentDescription,
        onErrorWhileLoadingImage = onErrorWhileLoadingImage,
        showLoadingShimmer = showLoadingShimmer,
        contentScale = contentScale
    )
}

@Composable
fun SyImage(
    modifier: Modifier,
    @DrawableRes image: Int,
    contentDescription: String,
    onErrorWhileLoadingImage: ((data: Any, e: Throwable) -> Unit)? = null,
    showLoadingShimmer: Boolean = false,
    contentScale : ContentScale = ContentScale.Crop
) {
    val request = ImageRequest.Builder(
        LocalContext.current
    ).data(
        image
    ).crossfade(
        true
    ).build()

    RealSyImage(
        modifier = modifier,
        imageRequest = request,
        contentDescription = contentDescription,
        onErrorWhileLoadingImage = onErrorWhileLoadingImage,
        showLoadingShimmer = showLoadingShimmer,
        contentScale = contentScale
    )
}

@Composable
private fun RealSyImage(
    modifier: Modifier,
    imageRequest: ImageRequest,
    contentDescription: String,
    onErrorWhileLoadingImage: ((data: Any, e: Throwable) -> Unit)? = null,
    showLoadingShimmer: Boolean = false,
    contentScale : ContentScale
) {

    if (showLoadingShimmer) {
        ShowLoadingShimmer(modifier)
    } else {

        SubcomposeAsyncImage(
            modifier = modifier,
            model = imageRequest,
            contentDescription = contentDescription,
            loading = {
                ShowLoadingShimmer(modifier)
            },
            onError = {
                onErrorWhileLoadingImage?.invoke(
                    imageRequest.data.toString(),
                    it.result.throwable
                )
            },
            contentScale = contentScale
        )
    }
}

@Composable
private fun ShowLoadingShimmer(modifier: Modifier) {
    Text(
        text = "Loading Image",
        modifier = modifier
            .showShimmer()
    )
}


