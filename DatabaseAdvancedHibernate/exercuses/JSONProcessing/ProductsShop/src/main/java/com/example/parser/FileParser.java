package com.example.parser;

public interface FileParser {
    String readFile(String path);

    void writeFile(String fileName,String file);
}
