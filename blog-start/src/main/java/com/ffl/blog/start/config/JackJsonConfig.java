package com.ffl.blog.start.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

import static com.ffl.blog.pojo.constant.DateConstants.YYYY_MM_DD_HH_MM_SS;

/**
 * @author lff
 * @datetime 2020/01/05 23:01
 */
@Configuration
public class JackJsonConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jackJsonObjectMapper(Jackson2ObjectMapperBuilder builder){
        builder.dateFormat(new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS));
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }
}
