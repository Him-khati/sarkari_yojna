package com.himanshu.sarkari_yojna.components.atoms.buttons

import androidx.compose.ui.graphics.Color

class ButtonConfig(builder: ButtonConfig.Builder) {

    val backgroundColor: Color?
    val backgroundColourDisabled: Color?

    val textColor: Color?
    val textColourDisabled: Color?

    val strokeColour: Color?
    val strokeColourDisabled: Color?

    init {
        this.backgroundColor = builder.backgroundColour
        this.backgroundColourDisabled = builder.backgroundColourDisabled

        this.textColor = builder.textColour
        this.textColourDisabled = builder.textColourDisabled

        this.strokeColour = builder.strokeColour
        this.strokeColourDisabled = builder.strokeColourDisabled
    }

    fun hasStrokeColours(): Boolean {
        return strokeColour != null && strokeColourDisabled != null
    }

    fun customTextColourProvidedByUser(): Boolean {
        return textColor != null && textColourDisabled != null
    }

    fun customBackgroundProvidedByUser(): Boolean {
        return backgroundColor != null && backgroundColourDisabled != null
    }

    class Builder {

        var backgroundColour: Color? = null
            private set
        var backgroundColourDisabled: Color? = null

        var textColour: Color? = null
            private set
        var textColourDisabled: Color? = null

        var strokeColour: Color? = null
            private set
        var strokeColourDisabled: Color? = null

        fun backgroundColor(
            backgroundColour: Color,
            backgroundColourDisabled: Color
        ) = apply {
            this.backgroundColour = backgroundColour
            this.backgroundColourDisabled = backgroundColourDisabled
        }


        fun textColor(
            textColour: Color,
            textColourDisabled: Color
        ) = apply {
            this.textColour = textColour
            this.textColourDisabled = textColourDisabled
        }


        fun strokeColor(
            strokeColour: Color,
            strokeColorDisabled: Color
        ) = apply {
            this.strokeColour = strokeColour
            this.strokeColourDisabled = strokeColorDisabled
        }

        fun build() = ButtonConfig(this)
    }
}