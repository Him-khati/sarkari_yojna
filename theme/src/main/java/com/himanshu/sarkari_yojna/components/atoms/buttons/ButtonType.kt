package com.himanshu.sarkari_yojna.components.atoms.buttons

enum class ButtonType {
    ElevatedButton,
    UnElevatedButton,
    OutlinedButton,
    TextButton,

    PositiveUnElevatedButton,
    PositiveOutlinedButton,

    NegativeUnElevatedButton,
    NegativeOutlinedButton;


    fun isButtonSolidElevatedButton(): Boolean {
        return this == ElevatedButton
    }

    fun isButtonSolidUnElevatedButton(): Boolean {
        return this == UnElevatedButton ||
                this == PositiveUnElevatedButton ||
                this == NegativeUnElevatedButton
    }

    fun isButtonSolidButton(): Boolean {
        return this == ElevatedButton ||
                this == UnElevatedButton ||
                this == PositiveUnElevatedButton ||
                this == NegativeUnElevatedButton
    }

    fun isButtonOutlinedButton(): Boolean {
        return this == OutlinedButton ||
                this == PositiveOutlinedButton ||
                this == NegativeOutlinedButton
    }
}