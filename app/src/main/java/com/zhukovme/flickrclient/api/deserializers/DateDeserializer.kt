package com.zhukovme.flickrclient.api.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

/**
 * Created by Michael Zhukov on 22.06.2018.
 * email: zhukovme@gmail.com
 */
class DateDeserializer : JsonDeserializer<Date> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {
        Timber.d("deserialize: ${json.asLong}")
        try {
            return Date(json.asLong)
        } catch (e: Exception) {
            Timber.e(e, "Error parsing date: %s", json.asString)
        }
        return null
    }
}
