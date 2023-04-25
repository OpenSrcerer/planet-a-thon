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

        // Set the content of the activity using Jetpack Compose.
        setContent {

            // Use the PatMobileTheme composable to apply the app's theme to the content.
            PatMobileTheme {

                // Remember the NavController so that it can maintain its state across configuration
                // changes.
                val controller = rememberNavController()

                // Specify the start destination of the NavHost.
                val startDestination = "home"

                // Create a NavHost composable to hold the destinations for the app's navigation.
                NavHost(controller, startDestination) {

                    // Define a composable destination (route) for the "home" screen and pass it the NavController
                    // instance.
                    composable("home") {
                        HomeView(controller)
                    }

                    // Define a composable destination (route) for the "cartoon" screen and pass it the NavController
                    // instance.
                    composable("cartoon") {
                        CartoonView(controller)
                    }
                }
            }
        }
    }

    /**
     * Adds a composable destination to the navigation graph.
     *
     * @param route The route of the composable destination.
     * @param arguments A list of named arguments associated with the destination.
     * @param deepLinks A list of deep links that can be used to navigate to the destination.
     * @param content A composable function that defines the UI for the destination.
     */
    private fun NavGraphBuilder.composable(
        route: String,
        arguments: List<NamedNavArgument> = emptyList(),
        deepLinks: List<NavDeepLink> = emptyList(),
        content: @Composable (NavBackStackEntry) -> Unit
    ) {
        addDestination(

            ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
                // Set the route of the destination.
                this.route = route

                // Add named arguments to the destination.
                arguments.forEach { (argumentName, argument) ->
                    addArgument(argumentName, argument)
                }

                // Add deep links to the destination.
                deepLinks.forEach { deepLink ->
                    addDeepLink(deepLink)
                }
            }
        )
    }
}