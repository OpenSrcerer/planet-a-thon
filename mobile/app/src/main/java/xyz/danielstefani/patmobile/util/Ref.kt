package xyz.danielstefani.patmobile.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

// Utility class that wraps mutable states in an easier to use fashion
open class Ref<T>(t: T) {
    private val state: MutableState<T> = mutableStateOf(t)

    fun value(): T { return state.value }

    infix fun to(t: T) { state.value = t }
}