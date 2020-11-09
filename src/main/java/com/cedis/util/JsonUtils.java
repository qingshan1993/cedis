package com.cedis.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author jjq
 * @version 1.0
 * @date 2020/8/23
 * @desc https://github.com/timo-reymann/spring-boot-date-and-time-starter.git
 */
@Slf4j
public class JsonUtils {

    public final static  String PATTERN_1 = "yyyy-MM-dd HH:mm:ss";

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JavaTimeModule javaTimeModule = new JavaTimeModule();
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(PATTERN_1);


    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(PATTERN_1));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        javaTimeModule.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer(dateTimeFormat));
        javaTimeModule.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer(dateTimeFormat));
        objectMapper.registerModule(javaTimeModule);
    }

    public static String object2json(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return  (obj instanceof String) ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Parse object to json Pretty error:{}", e.getMessage());
            return null;
        }
    }

    public static String object2jsonPretty(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return  (obj instanceof String) ? (String) obj :
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Parse object to json Pretty error:{}", e.getMessage());
            return null;
        }
    }

    public static <T> T json2object(String json, Class<T> clazz) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("read json convert object error:{}", e.getMessage());
            return null;
        }
    }


    public static <T> List<T> json2list(String json, Class<T> clazz) {
        if (json == null || "".equals(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>(){});
        } catch (JsonProcessingException e) {
            log.error("read json convert list error:{}", e.getMessage());
            return null;
        }
    }

    public static <T> String list2json(List<T> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error("parse list convert json error:{}", e.getMessage());
            return null;
        }
    }


}
