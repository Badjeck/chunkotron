package com.chuncking.fileTricks;

import com.chuncking.json.JsonUtils;

import java.io.FileOutputStream;
import java.util.List;

public class FileRecover {
    public static void RecoverFile(List<byte[]> chunklist) {
        String filePath = JsonUtils.getFilePath();
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(filePath,true);
            for(byte[]el : chunklist){
                fos.write(el);
                fos.flush();
            }
            fos.close();
        } catch ( Exception e){
            e.printStackTrace();
        }
    }
}
