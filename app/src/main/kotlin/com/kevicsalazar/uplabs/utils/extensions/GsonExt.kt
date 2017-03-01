package com.kevicsalazar.uplabs.utils.extensions

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import java.io.Reader
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType

/**
 * Created by Kevin.
 */

// Gson

inline fun <reified T: Any> Gson.fromJson(json: String): T = fromJson(json, typeToken<T>())

inline fun <reified T: Any> Gson.fromJson(json: Reader): T = fromJson(json, typeToken<T>())

inline fun <reified T: Any> Gson.fromJson(json: JsonReader): T = fromJson(json, typeToken<T>())

inline fun <reified T: Any> Gson.fromJson(json: JsonElement): T = fromJson(json, typeToken<T>())

// GsonBuilder

inline fun <reified T: Any> gsonTypeToken(): Type = object : TypeToken<T>() {} .type

fun ParameterizedType.isWildcard() : Boolean {
    var hasAnyWildCard = false
    var hasBaseWildCard = false
    var hasSpecific = false

    val cls = this.rawType as Class<*>
    cls.typeParameters.forEachIndexed { i, variable ->
        val argument = actualTypeArguments[i]

        if (argument is WildcardType) {
            val hit = variable.bounds.firstOrNull { it in argument.upperBounds }
            if (hit != null) {
                if (hit == Any::class.java)
                    hasAnyWildCard = true
                else
                    hasBaseWildCard = true
            }
            else
                hasSpecific = true
        }
        else
            hasSpecific = true

    }

    if (hasAnyWildCard && hasSpecific)
        throw Throwable("Either none or all type parameters can be wildcard in $this")

    return hasAnyWildCard || (hasBaseWildCard && !hasSpecific)
}

fun removeTypeWildcards(type: Type): Type {
    if (type is ParameterizedType) {
        val arguments = type.actualTypeArguments
                .map { if (it is WildcardType) it.upperBounds[0] else it }
                .map(::removeTypeWildcards)
                .toTypedArray()
        return TypeToken.getParameterized(type.rawType, *arguments).type
    }
    return type
}

inline fun <reified T: Any> typeToken(): Type {
    val type = gsonTypeToken<T>()

    if (type is ParameterizedType && type.isWildcard())
        return type.rawType

    return removeTypeWildcards(type)
}

// Element

val jsonNull: JsonNull = JsonNull.INSTANCE

val JsonElement.string: String get() = asString
val JsonElement?.nullString: String? get() = _nullOr { string }

val JsonElement.bool: Boolean get() = asBoolean
val JsonElement?.nullBool: Boolean? get() = _nullOr { bool }

val JsonElement.byte: Byte get() = asByte
val JsonElement?.nullByte: Byte? get() = _nullOr { byte }

val JsonElement.char: Char get() = asCharacter
val JsonElement?.nullChar: Char? get() = _nullOr { char }

val JsonElement.short: Short get() = asShort
val JsonElement?.nullShort: Short? get() = _nullOr { short }

val JsonElement.int: Int get() = asInt
val JsonElement?.nullInt: Int? get() = _nullOr { int }

val JsonElement.long: Long get() = asLong
val JsonElement?.nullLong: Long? get() = _nullOr { long }

val JsonElement.float: Float get() = asFloat
val JsonElement?.nullFloat: Float? get() = _nullOr { float }

val JsonElement.double: Double get() = asDouble
val JsonElement?.nullDouble: Double? get() = _nullOr { double }

val JsonElement.array: JsonArray get() = asJsonArray
val JsonElement?.nullArray: JsonArray? get() = _nullOr { array }

val JsonElement.obj: JsonObject get() = asJsonObject
val JsonElement?.nullObj: JsonObject? get() = _nullOr { obj }

private fun <T : Any> JsonElement?._nullOr(getNotNull: JsonElement.() -> T): T? = if (this == null || isJsonNull) null else getNotNull()