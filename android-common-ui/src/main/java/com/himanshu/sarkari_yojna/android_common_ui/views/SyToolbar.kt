package com.himanshu.sarkari_yojna.android_common_ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyToolbar(
    text : String,
    onNavigationBackClick : () -> Unit
){
    TopAppBar(
        title = { Text(text = text, style = MaterialTheme.typography.titleMedium)},
        navigationIcon = {
            IconButton(onClick = onNavigationBackClick) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(),
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Share, "Share")
            }
            IconButton(onClick = {}) {
                Icon(Icons.Filled.MailOutline, "MailOutline")
            }
        }
    )
}