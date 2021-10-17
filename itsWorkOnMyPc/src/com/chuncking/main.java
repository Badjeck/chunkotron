package com.chuncking;

import com.chuncking.api.BddPool;
import com.chuncking.chunk.Chunking;
import com.chuncking.chunk.DispachFile;
import com.chuncking.crypto.CryptoException;
import com.chuncking.crypto.CryptoUtils;
import com.chuncking.fileTricks.FileChooser;
import com.chuncking.fileTricks.FileRecover;
import com.chuncking.json.JsonUtils;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("(1): Save File\n(2): Get File\n(3): Exit");
        int choice = sc.nextInt();
        switch (choice){
            case 1 :
                SaveFile();
                System.out.println("file sended");
                System.exit(0);
            case 2 :
                GetFile();
                System.out.println("file recovered");
                System.exit(0);
            default:
                System.exit(0);
        }
    }

    public static void SaveFile(){
        Scanner sc = new Scanner(System.in);
        BddPool.connect();
        //generate salt for encryption
        String randomSalt = UUID.randomUUID().toString().replaceAll("-", "");
        JsonUtils.setSALT(randomSalt);

        //get file
        String filePath = FileChooser.getFile();
        File inputFile = new File(filePath);
        filePath = filePath + ".encrypted";
        File encryptedFile = new File(filePath);

        //chiffre le fichier
        System.out.println("Definissez un mots de passe : ");
        String mdp = sc.nextLine();
        try {
            byte[] key = CryptoUtils.getKeyFromPassword(mdp, randomSalt);
            CryptoUtils.encrypt(key, inputFile, encryptedFile);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (CryptoException e) {
            e.printStackTrace();
        }
//        comment diviser le fichier
        DispachFile.getFile(filePath);
//        diviser et envoyer
        DispachFile.splitFile();
    }

    public static void GetFile(){
        Scanner sc = new Scanner(System.in);
        BddPool.connect();
        JsonUtils.read();
        String pathFile = JsonUtils.getFilePath();
        List<byte[]> chunkList = BddPool.getToPool();
        FileRecover.RecoverFile(chunkList);

        System.out.println("mots de passe : ");
        String mdp = sc.nextLine();
        byte[] key = new byte[0];
        try {
            key = CryptoUtils.getKeyFromPassword(mdp, JsonUtils.getSALT());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        File encryptedFile = new File(pathFile);
        File decryptedFile = new File(pathFile.substring(0, pathFile.length() - 10));
        try {
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException e) {
            e.printStackTrace();
        }
    }

}


//=====================chiffrer et dechiffrer================================
//    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
////        File inputFile = new File("/home/gaby/workspace/chunkotron/testingPoint/test.txt");
//        File inputFile = new File(FileChooser.getFile());
//
//        File encryptedFile = new File("document.encrypted");
//        File decryptedFile = new File("document.decrypted");
//
//        String salt = "ici c'est random genre";
//
//        byte[] key = CryptoUtils.getKeyFromPassword("jambon", salt);
//
//        try {
//            CryptoUtils.encrypt(key, inputFile, encryptedFile);
//            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
//        } catch (CryptoException ex) {
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
//    }

//===============================chunk===========================
//public static void main(String[] args) {
//        Chunking.splitFile();
//        Chunking.mergeFile();
//    }

//============================Crud======================
//    public static void main(String[] args) {
//        Starfoulah.connect("127.0.0.1","3308","bdChunk","billy","bibobubibulbis");
//        try {
//            Starfoulah.insert(24,"ça marche");
//            Starfoulah.insert(4,"je vais mourir");
//            Starfoulah.selectAll();
//            System.out.printf("=======================================");
//            Starfoulah.updateOnId(4,"j'ai changé");
////            Starfoulah.delete(4);
////            Starfoulah.delete(24);
//            Starfoulah.selectAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }