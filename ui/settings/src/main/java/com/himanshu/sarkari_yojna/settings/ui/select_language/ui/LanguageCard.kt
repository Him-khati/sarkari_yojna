package com.himanshu.sarkari_yojna.settings.ui.select_language.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshu.sarkariyojna.android_base.language.Language

@Preview
@Composable
fun LanguageCardPreview() = LanguageCard(
    language = Language.English,
    selected = false,
    onLanguageCardClick = {}

)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageCard(
    language: Language,
    selected: Boolean,
    onLanguageCardClick: (language: Language) -> Unit
) = Card(
    modifier = Modifier
        .height(230.dp)
        .width(150.dp),
    onClick = {
        onLanguageCardClick(language)
    },
    shape = MaterialTheme.shapes.medium,
    colors = CardDefaults.elevatedCardColors(
        containerColor = language.colorForLanguage
    )
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = language.languageSymbol,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )
        Text(
            text = language.languageName,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}

