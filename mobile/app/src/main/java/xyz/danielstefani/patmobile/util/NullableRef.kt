package xyz.danielstefani.patmobile.util

// Like Stateable, but with nullable types
class NullableRef<T>(t: T?) : Ref<T?>(t)