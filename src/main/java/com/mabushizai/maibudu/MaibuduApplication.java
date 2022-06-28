package com.mabushizai.maibudu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mabushizai.maibudu.utils.LocalDateTimeToLongSerializer;
import com.mabushizai.maibudu.utils.LocalDateToLongSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.mabushizai.maibudu.dao"})
public class MaibuduApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaibuduApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateToLongSerializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeToLongSerializer());
        objectMapper.registerModule(javaTimeModule);

        SimpleModule simpleModule = new SimpleModule();
        // 序列化成json时,将所有的long变成string 因为js中得数字类型不能包含所有的java long值
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);

        // 序列换成json时,将所有的日期变成long型string
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return jackson2HttpMessageConverter;
    }
}
