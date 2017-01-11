package com.example.parser;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileParserImpl implements FileParser {
    @Override
    public String readFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();

        try (
            InputStream inputStream =getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ){
            while (true){
                String line = bufferedReader.readLine();
                if (line == null){
                    break;
                }

                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    @Override
    public void writeFile(String fileName,String file) {
        try (OutputStream os = new FileOutputStream(fileName);
             BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(os))
        ) {
            bfw.write(String.valueOf(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
