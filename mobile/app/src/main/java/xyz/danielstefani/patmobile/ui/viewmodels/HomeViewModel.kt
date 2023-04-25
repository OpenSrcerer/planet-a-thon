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

    private fun getCartoons() {
        CartoonHttpClient.getAllCartoons()
            .subscribe {
                val newCartoons = mutableMapOf<Char, MutableList<Cartoon>>()

                it.cartoons?.forEach { toon ->
                    val toonFirstChar = toon.name!!.uppercase()[0]
                    if (!newCartoons.containsKey(toonFirstChar)) {
                        newCartoons[toonFirstChar] = mutableListOf(toon)
                    } else {
                        newCartoons[toonFirstChar] =
                            newCartoons[toonFirstChar]!!.also { list ->
                                list.add(toon)
                            }
                    }
                }

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