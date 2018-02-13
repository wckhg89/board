package com.guppy.board.domain.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Created by guppy.kang on 2018. 2. 12.
 * email : guppy.kang@kakaocorp.com
 */
public class DateTimeSerializer  extends JsonSerializer<DateTime> {
    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // 원하는 timestamp 필드만을 serialize 한다.
        gen.writeNumber(value.getMillis());
    }
}
