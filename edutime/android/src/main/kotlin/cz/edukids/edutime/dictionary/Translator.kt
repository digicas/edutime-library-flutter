package cz.edukids.edutime.dictionary

import cz.edukids.sdk.model.TimeCategory
import kotlin.reflect.KProperty0

fun Any.toResponse(): Any = when (val t = this) {
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
    is Map<*, *> -> t
    // charsequence is not supported by default, but strings are
    is CharSequence -> t.toString()
    // lists can contain objects
    is List<*> -> t.map { it?.toResponse() }
    // TimeCategory needs to be translated to its id
    is TimeCategory -> t.id
    // translate every property to map entry
    else -> mutableMapOf<String, Any?>().also { map ->
        t::class.members.filterIsInstance<KProperty0<*>>().forEach {
            map[it.name] = it.get()?.toResponse()
        }
    }
}
