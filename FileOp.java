package com.assignment;

import java.io.*;
import java.util.ArrayList;

public class FileOp {
    /*read asset file*/
    public static DSALinkedList readAssetFile(String assetFilename){
        File file = new File(assetFilename);
        DSALinkedList assetList = new DSALinkedList();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null){
                /*split csv file cell*/
                String[] str = line.split(",");
                /*create a object to store the data*/
                AssetCell assetCell = new AssetCell(Integer.parseInt(str[0]),
                        str[1],str[2],
                        Double.parseDouble(str[3]),Double.parseDouble(str[4]),str[5],str[6],str[7]);
                assetList.add(assetCell);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assetList;

    }
    /*read trade file*/
    public static DSALinkedList readTradeFile(String tradeFilename){
        File file = new File(tradeFilename);
        DSALinkedList tradeList = new DSALinkedList();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null){
                String[] str = line.split(",");
                /*create a object to store the data*/
                TradeCell tradeCell = new TradeCell(str[0],
                        str[1],str[2],Double.parseDouble(str[3]),Double.parseDouble(str[4]),Double.parseDouble(str[5]),
                        Double.parseDouble(str[6]),Double.parseDouble(str[7]), Double.parseDouble(str[8]),Double.parseDouble(str[9]),
                        Double.parseDouble(str[10]),Double.parseDouble(str[11]),Double.parseDouble(str[12]),Double.parseDouble(str[13]),
                        Double.parseDouble(str[14]),Double.parseDouble(str[15]), Double.parseDouble(str[16]),Double.parseDouble(str[17]),
                        Double.parseDouble(str[18]),Double.parseDouble(str[19]),Double.parseDouble(str[20]),Double.parseDouble(str[21]),
                        Double.parseDouble(str[22]));
                tradeList.add(tradeCell);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tradeList;

    }
    /*save serialization file*/
    public static void saveSerFileList(String filename, DSALinkedList linkedList){
        File file = new File(filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(linkedList);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*save serialization file*/
    public static void saveSerFileTable(String filename, DSAHashtable hashtable){
        File file = new File(filename);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(hashtable);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*read serialization file*/
    public static DSALinkedList readSerFileList(String filename){
        File file = new File(filename);
        DSALinkedList list = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            list = (DSALinkedList) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    /*read serialization file*/
    public static DSAHashtable readSerFileTable(String filename){
        File file = new File(filename);
        DSAHashtable table = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            table = (DSAHashtable) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return table;
    }
}
