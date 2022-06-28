package com.mabushizai.maibudu.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * localDateTime转long型
 *
 * @author connor.h.liu
 * @company yzw
 * @date 2021-04-01 20:03
 */
public class LocalDateTimeToLongSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        long millis = instant.toEpochMilli();
        jsonGenerator.writeNumber(millis);
    }
}