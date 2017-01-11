package com.example.parser;

public interface JsonParser {
    <T> void writeToJson(T object,String path);

    <T> T readFromJson(Class<T>  object,String path);
}
