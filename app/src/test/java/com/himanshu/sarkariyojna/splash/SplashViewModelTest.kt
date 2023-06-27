package com.himanshu.sarkariyojna.splash

import app.cash.turbine.test
import com.himanshu.sarkari_yojna.domain.AppGlobalPreferencesManager
import com.himanshu.sarkariyojna.CoroutineRule
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.core.logger.Logger
import com.himanshu.sarkariyojna.ui.splash.SplashViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class SplashViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    private val testDispatcher = StandardTestDispatcher(
        TestCoroutineScheduler(),
        "test-dispatcher"
    )

    @Test
    fun getNavigationEvent_user_not_selected_language() = runBlocking {


        val appGlobalPreferencesManager: AppGlobalPreferencesManager = mockk(relaxed = true)
        val logger: Logger = mockk(relaxed = true)

        coEvery {
            appGlobalPreferencesManager.getUserSelectedLanguage()
        }.returns(
            null
        )

        val viewModel = SplashViewModel(
            appGlobalPreferencesManager,
            logger
        )

        viewModel
            .navigationEvent
            .flowOn(
                testDispatcher
            ).test {
                val result = this.awaitItem()
                assert(result is FromSplashScreenNavigation.NavigateToSelectLanguageScreen)
            }
    }

    @Test
    fun getNavigationEvent_user_has_selected_language() = runBlocking {

        val appGlobalPreferencesManager: AppGlobalPreferencesManager = mockk(relaxed = true)
        val logger: Logger = mockk(relaxed = true)


        coEvery {
            appGlobalPreferencesManager.getUserSelectedLanguage()
        }.returns(
            Language.English
        )

        val viewModel = SplashViewModel(
            appGlobalPreferencesManager,
            logger
        )

        viewModel
            .navigationEvent
            .flowOn(
                testDispatcher
            ).test {
                val result = this.awaitItem()
                assert(result is FromSplashScreenNavigation.NavigateToHomeScreen)
            }
    }

    @Test
    fun getNavigationEvent_user_language_selection_check_failed() = runBlocking {

        val appGlobalPreferencesManager: AppGlobalPreferencesManager = mockk(relaxed = true)
        val logger: Logger = mockk(relaxed = true)

        val fakeException = Exception("fake exception bro")
        coEvery {
            appGlobalPreferencesManager.getUserSelectedLanguage()
        } throws fakeException

        val viewModel = SplashViewModel(
            appGlobalPreferencesManager,
            logger
        )

        verify(
            exactly = 1
        ) {
            logger.e(
                "SplashViewModel",
                fakeException,
                "checkIfUserHasSelectedLanguageOrNot() : exception while checking user selected language"
            )
        }

        viewModel
            .navigationEvent
            .flowOn(
                testDispatcher
            ).test {
                val result = this.awaitItem()
                assert(result is FromSplashScreenNavigation.NavigateToHomeScreen)
            }
    }
}