package xyz.danielstefani.patmobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import xyz.danielstefani.patmobile.dto.Cartoon
import xyz.danielstefani.patmobile.util.Ref

class CartoonViewModel : ViewModel() {

    val currentCartoon = Ref(Cartoon())

    fun setCartoon(cartoon: Cartoon) {
        currentCartoon to cartoon
    }

    companion object {
        private var cartoonViewModel: CartoonViewModel? = null

        fun get(): CartoonViewModel {
            if (cartoonViewModel == null)
                cartoonViewModel = CartoonViewModel()
            return cartoonViewModel!!
        }
    }
}