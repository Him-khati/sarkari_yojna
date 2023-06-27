package com.himanshu.sarkari_yojna.components.atoms.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SyButtonSolidButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = Icons.Rounded.Share,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = ButtonConfig.Builder()
            .textColor(
                textColour = Color.Yellow,
                textColourDisabled = Color.Yellow
            ).build(),
        buttonType = ButtonType.UnElevatedButton,
        enabled = true
    )
}

@Preview
@Composable
fun SyButtonSolidPostiveButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = Icons.Rounded.Clear,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.PositiveUnElevatedButton
    )
}

@Preview
@Composable
fun SyButtonOutlinedButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.OutlinedButton,
        enabled = true
    )
}

@Preview
@Composable
fun SyButtonPositiveOutlinedButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.PositiveOutlinedButton,
        enabled = true
    )
}

@Preview
@Composable
fun SyButtonPositiveDisabledOutlinedButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.PositiveOutlinedButton,
        enabled = false
    )
}

@Preview
@Composable
fun SyButtonNegativeOutlinedButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.NegativeOutlinedButton
    )
}

@Preview
@Composable
fun SyButtonNegativeDisabledOutlinedButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.NegativeOutlinedButton,
        enabled = false
    )
}

@Preview
@Composable
fun SyButtonOutlinedButtonCustomConfigPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = ButtonConfig.Builder().strokeColor(
            strokeColour = Color.Black,
            strokeColorDisabled = Color.Gray
        ).textColor(
            textColour = Color.Black,
            textColourDisabled = Color.Gray
        ).build(),
        buttonType = ButtonType.OutlinedButton,
        enabled =  true
    )
}


@Preview
@Composable
fun SyButtonTextButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.TextButton
    )
}

@Preview
@Composable
fun SyButtonTextDisabledButtonPreview() {
    SyButton(
        text = "Button Text",
        onClick = {},
        icon = null,
        modifier = Modifier.fillMaxWidth(),
        buttonConfig = null,
        buttonType = ButtonType.TextButton,
        enabled = false
    )
}

