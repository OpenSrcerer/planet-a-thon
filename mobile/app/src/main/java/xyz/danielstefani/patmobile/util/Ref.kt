package xyz.danielstefani.patmobile.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * A utility class that wraps mutable states in an easier-to-use fashion.
 *
 * @param T The type of value stored in the reference.
 * @property state The mutable state that stores the value of the reference.
 */
open class Ref<T>(t: T) {
    // Initialize the mutable state with the initial value of the reference.
    private val state: MutableState<T> = mutableStateOf(t)

    /**
     * Returns the current value of the reference.
     *
     * @return The current value of the reference.
     */
    fun value(): T {
        return state.value
    }

    /**
     * Sets the value of the reference to the given value.
     *
     * @param t The value to set the reference to.
     */
    infix fun to(t: T) {
        state.value = t
    }
}