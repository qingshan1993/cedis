package com.cedis.backup;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer1 extends JsonSerializer<LocalDateTime> {

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public void serialize(LocalDateTime l, JsonGenerator j, SerializerProvider serializerProvider) throws IOException {
            ToStringSerializer.instance.serialize(dateTimeFormat.format(l), j, serializerProvider);
        }
    }