package com.mabushizai.maibudu.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

/**
 * localDate转long型
 *
 * @author connor.h.liu
 * @company yzw
 * @date 2021-04-01 20:03
 */
public class LocalDateToLongSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        Instant instant = localDate.atStartOfDay(ZoneOffset.systemDefault()).toInstant();
        long millis = instant.toEpochMilli();
        jsonGenerator.writeNumber(millis);
    }
}