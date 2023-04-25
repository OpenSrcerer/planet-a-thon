package xyz.danielstefani.patmobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import xyz.danielstefani.patmobile.client.cartoon.CartoonHttpClient
import xyz.danielstefani.patmobile.dto.Cartoon
import xyz.danielstefani.patmobile.util.Ref

class HomeViewModel : ViewModel() {

    val cartoons = Ref(mutableMapOf<Char, MutableList<Cartoon>>())

    init {
        getCartoons()
    }

    // Retrieve all cartoons from the CartoonHttpClient
    private fun getCartoons() {
        // Call the getAllCartoons function from the CartoonHttpClient and subscribe to the results
        CartoonHttpClient.getAllCartoons()
            .subscribe {
                // Create a new mutable map to hold the cartoons by their first character
                val newCartoons = mutableMapOf<Char, MutableList<Cartoon>>()

                // Iterate over each cartoon in the response
                it.cartoons?.forEach { toon ->
                    // Get the first character of the cartoon's name and convert it to uppercase
                    val toonFirstChar = toon.name!!.uppercase()[0]

                    // If the mutable map doesn't already contain a list for the first character, add a new list with the toon
                    if (!newCartoons.containsKey(toonFirstChar)) {
                        newCartoons[toonFirstChar] = mutableListOf(toon)
                    } else {
                        // If the mutable map already contains a list for the first character, add the toon to that list
                        newCartoons[toonFirstChar] =
                            newCartoons[toonFirstChar]!!.also { list ->
                                list.add(toon)
                            }
                    }
                }

                // Set the value of the "cartoons" variable to the new cartoons map
                cartoons to newCartoons
            }
    }

    companion object {
        private var homeViewModel: HomeViewModel? = null

        fun get(): HomeViewModel {
            if (homeViewModel == null)
                homeViewModel = HomeViewModel()
            return homeViewModel!!
        }
    }
}