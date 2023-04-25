package xyz.danielstefani.patmobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import xyz.danielstefani.patmobile.dto.Cartoon
import xyz.danielstefani.patmobile.util.Ref

// Define a Kotlin class for the CartoonViewModel that extends ViewModel
class CartoonViewModel : ViewModel() {

    // Define a variable to hold the current cartoon using an atomic reference
    val currentCartoon = Ref(Cartoon())

    // Define a function to set the current cartoon to the specified value
    fun setCartoon(cartoon: Cartoon) {
        currentCartoon to cartoon
    }

    // Define a companion object for the CartoonViewModel class
    companion object {
        // Define a private variable to hold the ViewModel instance
        private var cartoonViewModel: CartoonViewModel? = null

        // Define a static function to get the ViewModel instance
        fun get(): CartoonViewModel {
            // If the ViewModel instance is not yet created, create a new instance
            if (cartoonViewModel == null)
                cartoonViewModel = CartoonViewModel()
            // Return the ViewModel instance
            return cartoonViewModel!!
        }
    }
}