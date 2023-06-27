package com.himanshu.sarkari_yojna.components.atoms.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun SyButton(
    text: String,
    onClick: () -> Unit,
    icon: ImageVector?,
    modifier: Modifier = Modifier,
    buttonConfig: ButtonConfig? = null,
    buttonType: ButtonType = ButtonType.ElevatedButton,
    shape: Shape = MaterialTheme.shapes.large,
    enabled: Boolean = true
) {

    if (buttonType.isButtonSolidButton()) {
        SySolidButton(
            text = text,
            onClick = onClick,
            modifier = modifier,
            icon = icon,
            buttonConfig = buttonConfig,
            buttonType = buttonType,
            shape = shape,
            enabled = enabled
        )
    } else if (buttonType.isButtonOutlinedButton()) {
        SyOutlinedButton(
            text = text,
            onClick = onClick,
            modifier = modifier,
            icon = icon,
            buttonConfig = buttonConfig,
            buttonType = buttonType,
            shape = shape,
            enabled = enabled
        )
    } else {
        SyTextButton(
            text = text,
            onClick = onClick,
            modifier = modifier,
            buttonConfig = buttonConfig,
            buttonType = buttonType,
            shape = shape,
            enabled = enabled
        )
    }
}

@Composable
private fun SySolidButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    icon: ImageVector? = null,
    buttonConfig: ButtonConfig? = null,
    buttonType: ButtonType,
    shape: Shape,
    enabled: Boolean
) {

    val textColourEnabled = ButtonStyleProvider.getEnabledTextColor(
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )
    val textColourDisabled = ButtonStyleProvider.getDisabledTextColor(
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )

    val backgroundColorEnabled = ButtonStyleProvider.getButtonEnabledBackgroundColor(
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )
    val backgroundColorDisabled = ButtonStyleProvider.getButtonDisabledBackgroundColor(
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColorEnabled,
            contentColor = textColourEnabled,
            disabledContainerColor = backgroundColorDisabled,
            disabledContentColor = textColourDisabled
        ),
        elevation = if (buttonType.isButtonSolidElevatedButton()) ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 15.dp,
            focusedElevation = 10.dp
        ) else {
            ButtonDefaults.buttonElevation()
        }
    ) {

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "button-icon",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
private fun SyOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    icon: ImageVector? = null,
    buttonConfig: ButtonConfig? = null,
    buttonType: ButtonType,
    shape: Shape,
    enabled: Boolean
) {

    val strokeColor = ButtonStyleProvider.getStrokeColor(
        buttonEnabled = enabled,
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )

    val textColour = ButtonStyleProvider.getTextColor(
        buttonEnabled = enabled,
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )

    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        border = BorderStroke(
            width = 1.dp,
            color = strokeColor
        )
    ) {

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "button-icon",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        }

        Text(
            text = text,
            color = textColour,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun SyTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    icon: ImageVector? = null,
    buttonConfig: ButtonConfig? = null,
    buttonType: ButtonType,
    shape: Shape,
    enabled: Boolean
) {
    val textColour = ButtonStyleProvider.getTextColor(
        buttonEnabled = enabled,
        buttonType = buttonType,
        buttonConfig = buttonConfig
    )

    TextButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        enabled = enabled
    ) {

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "button-icon",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        }

        Text(
            text = text,
            color = textColour,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}