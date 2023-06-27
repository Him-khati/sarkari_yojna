package com.himanshu.sarkari_yojna.settings.ui.select_language.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshu.sarkariyojna.android_base.language.Language

@Preview
@Composable
fun SelectLanguageScreenLoadingPreview() = SelectLanguageScreenView(
    loadingLanguages = true,
    languages = listOf(),
    selectedLanguage = null,
    onLanguageClicked = {}
)

@Preview
@Composable
fun SelectLanguageScreenPreview() = SelectLanguageScreenView(
    loadingLanguages = false,
    languages = listOf(
        Language.English,
        Language.Hindi
    ),
    selectedLanguage = null,
    onLanguageClicked = {}
)



@Composable
fun SelectLanguageScreenView(
    loadingLanguages: Boolean,
    languages: List<Language>,
    selectedLanguage: Language?,
    onLanguageClicked: (language: Language) -> Unit
) = Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
) {

    //todo loading lang
    Spacer(modifier = Modifier.height(90.dp))
    Text(text = "Select Language")
    Text(text = "भाषा चुनिए")

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(languages) {
            LanguageCard(
                language = it,
                selected = false,
                onLanguageCardClick = onLanguageClicked
            )
        }
    }
}


