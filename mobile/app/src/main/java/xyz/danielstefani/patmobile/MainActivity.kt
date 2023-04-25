package xyz.danielstefani.patmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import xyz.danielstefani.patmobile.ui.theme.PatMobileTheme
import xyz.danielstefani.patmobile.ui.views.CartoonView
import xyz.danielstefani.patmobile.ui.views.HomeView

class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PatMobileTheme {
                val controller = rememberNavController()
                val startDestination = "home"

                NavHost(controller, startDestination) {
                    composable("home") {
                        HomeView(controller)
                    }

                    composable("cartoon") {
                        CartoonView(controller)
                    }
                }
            }
        }
    }

    // Extension function to make Navigation Graphs for composables
    private fun NavGraphBuilder.composable(
        route: String,
        arguments: List<NamedNavArgument> = emptyList(),
        deepLinks: List<NavDeepLink> = emptyList(),
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        addDestination(
            ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
                this.route = route
                arguments.forEach { (argumentName, argument) ->
                    addArgument(argumentName, argument)
                }
                deepLinks.forEach { deepLink ->
                    addDeepLink(deepLink)
                }
            }
        )
    }
}