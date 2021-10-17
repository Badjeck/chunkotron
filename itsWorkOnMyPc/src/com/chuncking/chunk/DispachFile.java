package com.chuncking.chunk;

import com.chuncking.api.BddPool;
import com.chuncking.fileTricks.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DispachFile {
    private static String FILE_PATH;
    private static File inputFile;
    private static byte PART_SIZE;
    private static String FILE_NAME;
    private static boolean addOneByteToTheFile = false;

    public static void getFile(String pathFile) {
        FILE_PATH = pathFile;
        inputFile = new File(FILE_PATH);
        FILE_NAME = inputFile.getName();
        System.out.println("You choose " + FILE_PATH);
        printDivisors();
    }

    public static void printDivisors() {
        Scanner sc = new Scanner(System.in);

        int fileSize = (int) inputFile.length();

        if (fileSize % 2 != 0) {
            addOneByteToTheFile = true;
            fileSize++;
        }

        int[] diviseur = new int[]{1};
        for (int i = 2; i <= fileSize; i++) {
            if (fileSize % i == 0) {
                diviseur = Arrays.copyOf(diviseur, diviseur.length + 1); //create new array from old array and allocate one more element
                diviseur[diviseur.length - 1] = i;
            }
        }
        System.out.println("Your file do " + fileSize + " bytes.");
        System.out.println("The smaller the chunks, the more secure the storage will be, but the longer the process time will be");
        System.out.println("Choose the chunk size you want");

        for (int i = 0; i < diviseur.length; i++) {
            System.out.println("(" + i + ") : " + diviseur[i]);
        }

        int selection = sc.nextInt();
        if (selection > diviseur.length) {
            System.out.println("you suck");
            System.exit(0);
        }
        PART_SIZE = (byte) diviseur[selection];
    }

    public static void splitFile() {
        List<byte[]> chunkArrays = new ArrayList<>();
        File inputFile = new File(FILE_PATH);
        FILE_NAME = inputFile.getName();
        FileInputStream inputStream;
        int fileSize = (int) inputFile.length();
        int nChunks = 0, read = 0, readLength = PART_SIZE;
        byte[] byteChunkPart;
        try {
            inputStream = new FileInputStream(inputFile);
            while (fileSize > 0) {
                if (fileSize <= PART_SIZE) {
                    readLength = fileSize;
                }
                byteChunkPart = new byte[readLength];
                read = inputStream.read(byteChunkPart, 0, readLength);
                fileSize -= read;
                assert (read == byteChunkPart.length);
                nChunks++;
                chunkArrays.add(byteChunkPart);
                System.out.println(byteChunkPart);
                byteChunkPart = null;
            }
            inputStream.close();
            BddPool.sendToPool(chunkArrays,FILE_PATH);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
