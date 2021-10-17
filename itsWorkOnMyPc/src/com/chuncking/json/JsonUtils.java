package com.chuncking.json;

import com.chuncking.fileTricks.FileChooser;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonReader;


public class JsonUtils {

    public static String FILE_PATH;
    public static String SALT;
    public static List<String[]> COPY_1;
    public static List<String[]> COPY_2;


    public static void write(String filePath, List<String[]> copy1, List<String[]> copy2) {
        Model modelObj = new Model();

        modelObj.setFilePath(filePath);
        modelObj.setKey(SALT);
        modelObj.setCopy1(copy1);
        modelObj.setCopy2(copy2);

        Gson gson = new Gson();

//        convert java object to JSON format,
//        and returned as JSON formatted string

        String json = gson.toJson(modelObj);
        try {
            FileWriter writer = new FileWriter(filePath + ".json");
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read() {

        Gson gson = new Gson();

        try {

            System.out.println("Reading JSON from a file");
            System.out.println("----------------------------");

            String filePath = FileChooser.getFile();

            JsonReader br = new JsonReader(
                    new FileReader(filePath));

            //convert the json string back to object
            Country countryObj = gson.fromJson(br, Country.class);

            FILE_PATH = countryObj.getFilePath();
            SALT = countryObj.getKey();
            COPY_1 = countryObj.getCopy1();
            COPY_2 = countryObj.getCopy2();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setSALT(String KEY) {
        JsonUtils.SALT = KEY;
    }

    public static String getSALT() {
        return SALT;
    }

    public static List<String[]> getCopy1() {
        return COPY_1;
    }

    public static List<String[]> getCopy2() {
        return COPY_2;
    }
}
