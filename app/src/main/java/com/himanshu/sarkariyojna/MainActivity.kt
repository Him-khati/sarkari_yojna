package com.himanshu.sarkariyojna

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.himanshu.sarkari_yojna.NavigationDestination
import com.himanshu.sarkari_yojna.theme.SarkariYojnaTheme
import com.himanshu.sarkari_yojna.NavigationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navigationViewModel: NavigationViewModel by viewModels()
    private val mainViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent{
            SarkariYojnaTheme {
                SarkariYojnaComposeApp()
            }
        }

        mainViewModel.lookForDeepLinksAndActions(intent)
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mainViewModel.handleOnNewIntent(intent)
    }


    private fun handleNavigationEvent(
        it: NavigationDestination
    ) {
    }
}