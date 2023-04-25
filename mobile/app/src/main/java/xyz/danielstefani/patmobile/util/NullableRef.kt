package xyz.danielstefani.patmobile.util

// Like Ref, but with nullable types
class NullableRef<T>(t: T?) : Ref<T?>(t)