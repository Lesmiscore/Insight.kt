package com.nao20010128nao.Insight

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.MediaType
import okhttp3.RequestBody
import java.lang.reflect.Type
import java.net.URLEncoder
import java.time.LocalDate
import kotlin.reflect.KClass

val BitcoinVIn2.isCoinCreation: Boolean
    get() = coinbase != null

internal inline val LocalDate.insightExpression: String
    get() = "$year-$monthValue-$dayOfMonth"

internal inline fun <reified E> Moshi.listAdapter(elementType: Type = E::class.java): JsonAdapter<List<E>> {
    return adapter(listType<E>(elementType))
}

internal inline fun <reified K, reified V> Moshi.mapAdapter(
        keyType: Type = K::class.java,
        valueType: Type = V::class.java): JsonAdapter<Map<K, V>> {
    return adapter(mapType<K, V>(keyType, valueType))
}

internal inline fun <reified E> listType(elementType: Type = E::class.java): Type {
    return Types.newParameterizedType(List::class.java, elementType)
}

internal inline fun <reified K, reified V> mapType(
        keyType: Type = K::class.java,
        valueType: Type = V::class.java): Type {
    return Types.newParameterizedType(Map::class.java, keyType, valueType)
}

internal inline fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }

internal inline fun Map<String, String>.toQueryString(): String =
        entries.joinToString("&") { "${URLEncoder.encode(it.key, "UTF-8")}=${URLEncoder.encode(it.value, "UTF-8")}" }

internal inline fun <T : Any> Moshi.adapter(clz: KClass<T>): JsonAdapter<T> = adapter(clz.java)
internal inline fun <reified T : Any> Moshi.adapter(): JsonAdapter<T> = adapter(T::class)

internal inline fun requestBody(mimeType: String, text: String): RequestBody = RequestBody.create(MediaType.parse(mimeType), text)
