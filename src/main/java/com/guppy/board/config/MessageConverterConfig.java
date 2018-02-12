package com.guppy.board.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guppy.board.domain.module.DateTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by guppy.kang on 2018. 2. 12.
 * email : guppy.kang@kakaocorp.com
 */

@Configuration
public class MessageConverterConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        // 생성한 모듈을 등록해 준다.
        objectMapper.registerModule(new DateTimeModule());
        jsonConverter.setObjectMapper(objectMapper);

        return jsonConverter;
    }

}
