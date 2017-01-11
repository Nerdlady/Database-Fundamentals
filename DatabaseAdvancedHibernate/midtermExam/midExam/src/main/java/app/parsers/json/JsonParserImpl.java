package app.parsers.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonParserImpl implements JsonParser {
    private Gson gson;

    public JsonParserImpl() {
        this.setGson(new GsonBuilder().setPrettyPrinting().create());
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T[] readFromJson(Class<T[]> classes, String file) {
        String fileData = null;
        T[] objects = null;
        InputStream inputFile = getClass().getResourceAsStream(file);
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(inputFile));
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        objects = this.gson.fromJson(jsonData.toString(), classes);

        return objects;
    }

    @Override
    public <T> void writeToJson(T object, String file) {
        String json = this.getGson().toJson(object);
        try (
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        ) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
