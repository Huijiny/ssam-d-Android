package com.mashup.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.presentation.KeyLinkAppState
import com.mashup.presentation.feature.chat.navigation.chatGraph
import com.mashup.presentation.feature.detail.chat.navigation.navigateToChatDetail
import com.mashup.presentation.feature.detail.message.navigation.navigateToMessageDetail
import com.mashup.presentation.feature.home.navigation.homeGraph
import com.mashup.presentation.feature.home.navigation.navigateToGuideRoute
import com.mashup.presentation.feature.home.navigation.navigateToSubscribeKeywordRoute
import com.mashup.presentation.feature.report.navigation.navigateToReport
import com.mashup.presentation.feature.signal.navigation.signalGraph

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@Composable
fun KeyLinkNavHost(
    appState: KeyLinkAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = KeyLinkNavigationRoute.HomeGraph.route
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph(
            onSubscribeKeywordClick = navController::navigateToSubscribeKeywordRoute,
            onGuideClick = navController::navigateToGuideRoute,
            onBackClick = navController::navigateUp
        )
        signalGraph(
            navController = navController,
            onBackClick = navController::navigateUp,
        )
        chatGraph(
            onBackClick = navController::navigateUp,
            onChatClick = navController::navigateToChatDetail,
            onMessageClick = navController::navigateToMessageDetail,
            onReportIconClick = navController::navigateToReport,
            onReplyButtonClick = {},
        )
    }
}