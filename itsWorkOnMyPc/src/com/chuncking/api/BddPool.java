package com.chuncking.api;

import com.chuncking.json.JsonUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

public class BddPool {
    private static BddUtils bdd1;
    private static BddUtils bdd2;
    private static BddUtils bdd3;
    private static BddUtils bdd4;
    private static BddUtils bdd5;
    private static int nbBdd;
    private static List<BddUtils> pool;

    public static void connect() {
        bdd1 = new BddUtils("127.0.0.1","3308","bdChunk","billy","bibobubibulbis");
        bdd2 = new BddUtils("127.0.0.1","3309","bdChunk","billy","bibobubibulbis");
        bdd3 = new BddUtils("127.0.0.1","3308","bdChunk","billy","bibobubibulbis");
        bdd4 = new BddUtils("127.0.0.1","3308","bdChunk","billy","bibobubibulbis");
        bdd5 = new BddUtils("127.0.0.1","3308","bdChunk","billy","bibobubibulbis");
        nbBdd = 5;
        pool = Arrays.asList(bdd1,bdd2,bdd3,bdd4,bdd5);
    }

    public static void sendToPool(List<byte[]> chunkList, String filePath) throws IOException {
        List<String[]> copy1 = new ArrayList<>();
        List<String[]> copy2 = new ArrayList<>();
        FileOutputStream filePart;

        for (byte[] el:chunkList) {
            String uuid = UUID.randomUUID().toString();
            int location1 = ThreadLocalRandom.current().nextInt(1, 5 + 1);
            int location2 = location1;

            while(location2 == location1)
                location2 = ThreadLocalRandom.current().nextInt(1, 5 + 1);

            //Save all location and id by copy
            String[] save1 = {String.valueOf(location1),uuid};
            copy1.add(save1);
            String[] save2 = {String.valueOf(location2),uuid};
            copy2.add(save2);


            try {
                pool.get(location1 - 1).insert(uuid,el);
                pool.get(location2 - 1).insert(uuid,el);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JsonUtils.write(filePath,copy1,copy2);
    }

    public static List<byte[]> getToPool() {
        System.out.println("Get file chunk from bdd");
        List<String[]> chunckLocation = JsonUtils.getCopy1();
        List<byte[]> chunkList = new ArrayList<>();

        for(String[] el : chunckLocation) {
            int location = parseInt(el[0]);
            try {
                byte[] chunk = pool.get(location-1).selectById(el[1]);
                chunkList.add(chunk);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chunkList;

//        for (String el: idlist) {
//            try {
//                chunklist.add(bdd1.selectById(el));
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        int non = 0;
//        for (byte[] el: chunkList) {
//            String newFileName = "/home/gaby/workspace/chunkotron/testingPoint/test.txt.part"
//                    + Integer.toString(non);
//            byte[] byteChunkPart = el;
//            filePart = new FileOutputStream(new File(newFileName));
//            filePart.write(byteChunkPart);
//            filePart.flush();
//            filePart.close();
//            byteChunkPart = null;
//            filePart = null;
//            non++;
//        }
    }

    public static void deleteAllToPool() {
        for (BddUtils bdd : pool){
            try {
                bdd.deleteAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
