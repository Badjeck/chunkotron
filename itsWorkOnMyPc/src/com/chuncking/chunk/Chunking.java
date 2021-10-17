package com.chuncking.chunk;

import com.chuncking.api.BddPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Chunking {
    private static String FILE_NAME = "/home/gaby/workspace/chunkotron/testingPoint/test.txt";
    private static String NAME_FILE;
    private static byte PART_SIZE = 5;

    public static void splitFile() {
        List<byte[]> chunkArrays = new ArrayList<>();
        File inputFile = new File(FILE_NAME);
        NAME_FILE = inputFile.getName();
        FileInputStream inputStream;
        int fileSize = (int) inputFile.length();
        int nChunks = 0, read = 0, readLength = PART_SIZE;
        byte[] byteChunkPart;
        try {
            inputStream = new FileInputStream(inputFile);
            while (fileSize > 0) {
                if (fileSize <= 5) {
                    readLength = fileSize;
                }
                byteChunkPart = new byte[readLength];
                read = inputStream.read(byteChunkPart, 0, readLength);
                fileSize -= read;
                assert (read == byteChunkPart.length);
                nChunks++;
                chunkArrays.add(byteChunkPart);
                byteChunkPart = null;
            }
            inputStream.close();
            BddPool.sendToPool(chunkArrays,FILE_NAME);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void mergeFile() {
        File ofile = new File(FILE_NAME);
        FileOutputStream fos;
        FileInputStream fis;
        byte[] fileBytes;
        int bytesRead = 0;
        List list = new ArrayList();
        list.add(new File(FILE_NAME + ".part0"));
        list.add(new File(FILE_NAME + ".part1"));
        list.add(new File(FILE_NAME + ".part2"));
        list.add(new File(FILE_NAME + ".part3"));
        list.add(new File(FILE_NAME + ".part4"));
        list.add(new File(FILE_NAME + ".part5"));
        list.add(new File(FILE_NAME + ".part6"));
        list.add(new File(FILE_NAME + ".part7"));
        list.add(new File(FILE_NAME + ".part8"));
        list.add(new File(FILE_NAME + ".part9"));
        list.add(new File(FILE_NAME + ".part10"));
        list.add(new File(FILE_NAME + ".part11"));
        list.add(new File(FILE_NAME + ".part12"));
        list.add(new File(FILE_NAME + ".part13"));
        list.add(new File(FILE_NAME + ".part14"));
        list.add(new File(FILE_NAME + ".part15"));
        list.add(new File(FILE_NAME + ".part16"));
        list.add(new File(FILE_NAME + ".part17"));
        list.add(new File(FILE_NAME + ".part18"));
        list.add(new File(FILE_NAME + ".part19"));
        list.add(new File(FILE_NAME + ".part20"));
        list.add(new File(FILE_NAME + ".part21"));
        list.add(new File(FILE_NAME + ".part22"));
        list.add(new File(FILE_NAME + ".part23"));
        list.add(new File(FILE_NAME + ".part24"));
        list.add(new File(FILE_NAME + ".part25"));
        list.add(new File(FILE_NAME + ".part26"));
        list.add(new File(FILE_NAME + ".part27"));
        list.add(new File(FILE_NAME + ".part28"));
        list.add(new File(FILE_NAME + ".part29"));
        list.add(new File(FILE_NAME + ".part30"));
        list.add(new File(FILE_NAME + ".part31"));
        list.add(new File(FILE_NAME + ".part32"));
        list.add(new File(FILE_NAME + ".part33"));
        list.add(new File(FILE_NAME + ".part34"));
        try {
            fos = new FileOutputStream("/home/gaby/workspace/chunkotron/testingPoint/stp.txt", true);
            for (int i = 0; i < list.size(); i++) {
                File file = (File) list.get(i);
                fis = new FileInputStream(file);
                fileBytes = new byte[(int) file.length()];
                bytesRead = fis.read(fileBytes, 0, (int) file.length());
                assert (bytesRead == fileBytes.length);
                assert (bytesRead == (int) file.length());
                fos.write(fileBytes);
                fos.flush();
                fileBytes = null;
                fis.close();
                fis = null;
            }
            fos.close();
            fos = null;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
//    public static void splitFile() {
//        FileInputStream inputStream;
//        String newFileName;
//        FileOutputStream filePart;
//        int fileSize = (int) inputFile.length();
//        int nChunks = 0, read = 0, readLength = PART_SIZE;
//        byte[] byteChunkPart;
//        try {
//            inputStream = new FileInputStream(inputFile);
//            while (fileSize > 0) {
//                if (fileSize <= 5) {
//                    readLength = fileSize;
//                }
//                byteChunkPart = new byte[readLength];
//                read = inputStream.read(byteChunkPart, 0, readLength);
//                fileSize -= read;
//                assert (read == byteChunkPart.length);
//                nChunks++;
//                newFileName = FILE_NAME + ".part"
//                        + Integer.toString(nChunks - 1);
//                filePart = new FileOutputStream(new File(newFileName));
//                filePart.write(byteChunkPart);
//                filePart.flush();
//                filePart.close();
//                byteChunkPart = null;
//                filePart = null;
//            }
//            inputStream.close();
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }

}
