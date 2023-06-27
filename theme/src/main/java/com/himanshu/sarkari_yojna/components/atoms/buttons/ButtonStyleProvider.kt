package com.himanshu.sarkari_yojna.components.atoms.buttons

import androidx.compose.ui.graphics.Color
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.defaultButton
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.defaultOutlinedButton
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.defaultTextButton
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.negativeOutlinedButton
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.negativeUnElevatedButton
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.positiveOutlinedButton
import com.himanshu.sarkari_yojna.components.atoms.buttons.configs.DefaultButtonConfigs.positiveUnElevatedButton

object ButtonStyleProvider {

    fun getTextColor(
        buttonEnabled: Boolean,
        buttonType: ButtonType,
        buttonConfig: ButtonConfig?
    ): Color {

        return if (buttonEnabled) getEnabledTextColor(
            buttonType = buttonType,
            buttonConfig = buttonConfig
        ) else getDisabledTextColor(
            buttonType = buttonType,
            buttonConfig = buttonConfig
        )
    }

    fun getEnabledTextColor(
        buttonType: ButtonType,
        buttonConfig: ButtonConfig?
    ): Color {
        val finalButtonConfig = if (buttonConfig?.customTextColourProvidedByUser() == true) {
            buttonConfig
        } else if (buttonType == ButtonType.PositiveOutlinedButton) {
            positiveOutlinedButton
        } else if (buttonType == ButtonType.NegativeOutlinedButton) {
            negativeOutlinedButton
        } else if (buttonType == ButtonType.OutlinedButton) {
            defaultOutlinedButton
        } else if (buttonType == ButtonType.TextButton) {
            defaultTextButton
        } else {
            defaultButton
        }

        return finalButtonConfig.textColor!!
    }

    fun getDisabledTextColor(
        buttonType: ButtonType,
        buttonConfig: ButtonConfig?
    ): Color {
        val finalButtonConfig = if (buttonConfig?.customTextColourProvidedByUser() == true) {
            buttonConfig
        } else if (buttonType == ButtonType.PositiveOutlinedButton) {
            positiveOutlinedButton
        } else if (buttonType == ButtonType.NegativeOutlinedButton) {
            negativeOutlinedButton
        } else if (buttonType == ButtonType.OutlinedButton) {
            defaultOutlinedButton
        }  else if (buttonType == ButtonType.TextButton) {
            defaultTextButton
        } else {
            defaultButton
        }

        return finalButtonConfig.textColourDisabled!!
    }

    fun getButtonEnabledBackgroundColor(
        buttonType: ButtonType,
        buttonConfig: ButtonConfig?
    ): Color {

        if(!buttonType.isButtonSolidButton()){
            error("getButtonEnabledBackgroundColor() can only be called for solid button")
        }

        val finalButtonConfig = if (buttonConfig?.customBackgroundProvidedByUser() == true) {
            buttonConfig
        } else if (buttonType == ButtonType.ElevatedButton || buttonType == ButtonType.UnElevatedButton) {
            defaultButton
        } else if (buttonType == ButtonType.PositiveUnElevatedButton) {
            positiveUnElevatedButton
        } else if (buttonType == ButtonType.NegativeUnElevatedButton) {
            negativeUnElevatedButton
        } else {
            defaultButton
        }

        return finalButtonConfig.backgroundColor!!
    }

    fun getButtonDisabledBackgroundColor(
        buttonType: ButtonType,
        buttonConfig: ButtonConfig?
    ): Color {
        if(!buttonType.isButtonSolidButton()){
            error("getButtonEnabledBackgroundColor() can only be called for solid button")
        }

        val finalButtonConfig = if (buttonConfig?.customBackgroundProvidedByUser() == true) {
            buttonConfig
        } else if (buttonType == ButtonType.ElevatedButton || buttonType == ButtonType.UnElevatedButton) {
            defaultButton
        } else if (buttonType == ButtonType.PositiveUnElevatedButton) {
            positiveUnElevatedButton
        } else if (buttonType == ButtonType.NegativeUnElevatedButton) {
            negativeUnElevatedButton
        } else {
            defaultButton
        }

        return finalButtonConfig.backgroundColourDisabled!!
    }


    fun getStrokeColor(
        buttonEnabled: Boolean,
        buttonType: ButtonType,
        buttonConfig: ButtonConfig?
    ): Color {

        if (!buttonType.isButtonOutlinedButton()) {
            error("getStrokeColor() called for a button that's not a outlined button")
        }

        val finalButtonConfig = if (buttonConfig?.hasStrokeColours() == true) {
            buttonConfig
        } else if (buttonType == ButtonType.PositiveOutlinedButton) {
            positiveOutlinedButton
        } else if (buttonType == ButtonType.NegativeOutlinedButton) {
            negativeOutlinedButton
        } else {
            defaultOutlinedButton
        }

        return if (buttonEnabled) finalButtonConfig.strokeColour!! else finalButtonConfig.strokeColourDisabled!!
    }

}