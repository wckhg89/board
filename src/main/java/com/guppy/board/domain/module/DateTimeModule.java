package com.guppy.board.domain.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.DateTime;

/**
 * Created by guppy.kang on 2018. 2. 12.
 * email : guppy.kang@kakaocorp.com
 */
public class DateTimeModule extends SimpleModule{

    public DateTimeModule() {
        super();
        // 모듈에 Serializer를 등록해 준다.
        addSerializer(DateTime.class, new DateTimeSerializer());
    }
}
