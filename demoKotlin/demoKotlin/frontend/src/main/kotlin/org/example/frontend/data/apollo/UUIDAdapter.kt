package org.example.frontend.data.apollo

import com.apollographql.apollo3.api.Adapter
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.api.json.JsonReader
import com.apollographql.apollo3.api.json.JsonWriter
import java.util.*

val uuidAdapter = object : Adapter<UUID> {
    override fun fromJson(reader: JsonReader, customScalarAdapters: CustomScalarAdapters): UUID {
        TODO()
    }

    override fun toJson(writer: JsonWriter, customScalarAdapters: CustomScalarAdapters, value: UUID) {
        value.toString()
    }


}
