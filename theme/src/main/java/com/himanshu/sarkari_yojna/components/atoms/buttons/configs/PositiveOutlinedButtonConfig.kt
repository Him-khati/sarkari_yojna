package com.himanshu.sarkari_yojna.components.atoms.buttons.configs

import androidx.compose.ui.graphics.Color
import com.himanshu.sarkari_yojna.components.atoms.buttons.ButtonConfig
import com.himanshu.sarkari_yojna.theme.Green80
import com.himanshu.sarkari_yojna.theme.Red40

object DefaultButtonConfigs {


    /**
     * Solid Buttons
     */

    val defaultButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Color.Blue,
            backgroundColourDisabled = Color.Gray
        ).textColor(
            textColour = Color.White,
            textColourDisabled = Color.White
        ).build()

    val positiveUnElevatedButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Green80,
            backgroundColourDisabled = Color.White
        ).textColor(
            textColour = Color.White,
            textColourDisabled = Color.White
        ).build()

    val negativeUnElevatedButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Red40,
            backgroundColourDisabled = Color.Gray
        ).textColor(
            textColour = Color.White,
            textColourDisabled = Color.White
        ).build()


    /**
     * Outlined Buttons
     */

    val defaultOutlinedButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Color.White,
            backgroundColourDisabled = Color.White
        ).strokeColor(
            strokeColour = Green80,
            strokeColorDisabled = Color.Gray
        ).textColor(
            textColour = Green80,
            textColourDisabled = Color.Gray
        ).build()

    val positiveOutlinedButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Color.White,
            backgroundColourDisabled = Color.White
        ).strokeColor(
            strokeColour = Green80,
            strokeColorDisabled = Color.Gray
        ).textColor(
            textColour = Green80,
            textColourDisabled = Color.Gray
        ).build()

    val negativeOutlinedButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Color.White,
            backgroundColourDisabled = Color.White
        ).strokeColor(
            strokeColour = Red40,
            strokeColorDisabled = Color.Gray
        ).textColor(
            textColour = Red40,
            textColourDisabled = Color.Gray
        ).build()


    /**
     * ------------------------
     * Text Buttons
     * ------------------------
     */

    val defaultTextButton = ButtonConfig.Builder()
        .backgroundColor(
            backgroundColour = Color.White,
            backgroundColourDisabled = Color.White
        ).strokeColor(
            strokeColour = Color.White,
            strokeColorDisabled = Color.White
        ).textColor(
            textColour = Red40,
            textColourDisabled = Color.Gray
        ).build()

}