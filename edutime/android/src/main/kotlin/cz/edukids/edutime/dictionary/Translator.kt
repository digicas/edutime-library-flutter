package cz.edukids.edutime.dictionary

import kotlin.reflect.KProperty0

fun Any.toResponse(): Any = when (this) {
    // primitives
    is Number,
    is Boolean,
        // arrays
    is String,
    is IntArray,
    is ByteArray,
    is LongArray,
    is DoubleArray,
        // final type, we trust that map doesn't contain anything else than primitives
    is Map<*, *> -> this
    // charsequence is not supported by default, but strings are
    is CharSequence -> toString()
    // lists can contain objects
    is List<*> -> map { it?.toResponse() }
    // translate every property to map entry
    else -> mutableMapOf<String, Any?>().also { map ->
        this@toResponse::class.members.filterIsInstance<KProperty0<*>>().forEach {
            map[it.name] = it.get()?.toResponse()
        }
    }
}
