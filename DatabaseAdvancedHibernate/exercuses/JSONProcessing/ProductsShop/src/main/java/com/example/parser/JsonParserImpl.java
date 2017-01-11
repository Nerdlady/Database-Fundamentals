package com.example.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonParserImpl implements JsonParser {
    private Gson gson;

    @Autowired
    private FileParser fileParser;

    public JsonParserImpl() {
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Override
    public <T> void writeToJson(T object, String path) {
        String content = this.gson.toJson(object);
        this.fileParser.writeFile(path, content);
    }

    @Override
        public <T> T readFromJson(Class<T> object, String path) {
        T element = null;
        String file = this.fileParser.readFile(path);
        element = this.gson.fromJson(file, object);
        return element;
    }
}
