package me.absolute.springcourse.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.absolute.springcourse.Library;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    private static String path = "src/main/resources/config.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static BenchConfig benchConfig;
    private static String json;

    static {
        try {
            loadJson(path);
        } catch (IOException e) {
            System.out.println("JSON file not found");
        }
    }

    public static BenchConfig getBenchConfig() {
        if(benchConfig == null) System.out.println("Config is null!");
        return benchConfig;
    }

    public static void writeJson() {
        json = GSON.toJson(benchConfig);
        System.out.println(json);
    }

    public static boolean saveJson(String path) throws IOException {
        try {
            json = GSON.toJson(benchConfig);
            PrintWriter writer = new PrintWriter(new FileWriter(path));
            writer.write(json);
            writer.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("JSON file not found");
            return false;
        }
    }

    public static boolean loadJson(String path) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String jsonImage = "";
            while (reader.ready()){
                jsonImage += reader.readLine();
            }
            BenchConfig config = GSON.fromJson(jsonImage, BenchConfig.class);
            benchConfig = config;
            reader.close();
            return true;
        }
        catch (FileNotFoundException e) {
            System.out.println("JSON file not found");
            return false;
        }
    }


}

