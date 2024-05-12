package com.studenthelper.data.serialization

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias LocalDateTimeSerializerJson = @Serializable(with = DateSerializer::class) LocalDateTime

object DateSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime =
        Instant.parse(decoder.decodeString()).toLocalDateTime(TimeZone.currentSystemDefault())

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        value.toInstant(TimeZone.currentSystemDefault()).toString()
    }
}