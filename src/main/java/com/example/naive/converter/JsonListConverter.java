package com.example.naive.converter;

import com.alibaba.fastjson2.JSON;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class JsonListConverter implements AttributeConverter<List<Object>, String> {

    @Override
    public String convertToDatabaseColumn(List<Object> objects) {
        return JSON.toJSONString(objects);
    }

    @Override
    public List<Object> convertToEntityAttribute(String s) {
        return JSON.parseArray(s, Object.class);
    }
}
