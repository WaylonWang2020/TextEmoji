package com.sctdroid.app.wallpapertodo.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lixindong on 4/19/17.
 */

public class FileAccessUtils {

    public static void write(String path, String data) {
        if (!TextUtils.isEmpty(path)) {
            try {
                FileWriter writer = new FileWriter(path);
                writer.write(data);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String read(String path) {
        if (!TextUtils.isEmpty(path)) {
            try {
                File file = new File(path);
                if (file.exists()) {
                    StringBuilder text = new StringBuilder();
                    BufferedReader reader = null;
                    reader = new BufferedReader(new FileReader(file));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        text.append(line);
                    }
                    reader.close();
                    return text.toString();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
