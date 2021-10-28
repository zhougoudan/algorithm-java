package com.assignment;

import java.io.*;

public class DSAHashtable implements Serializable {
    private HashEntry[] hashPlace;
    private final float threshold;
    private int count = 0;//record number of data
    private int maxSize;
    /*inner class*/
    private static class HashEntry implements Serializable{
        public String key;
        public Object value;
        HashEntry next = null;

        HashEntry(String key,Object value){
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
    /*my hashcode function*/
    private int hashCode(int num){
        num = Math.abs(num);
        return num % (maxSize - 7);
    }
    /*initialize*/
    DSAHashtable(int threshold, int maxSize){
        this.maxSize = maxSize;
        this.threshold = threshold;
        hashPlace = new HashEntry[maxSize];
    }
    /*read Asset File*/
    private boolean readAssetFile(File file){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine() ;
            String[] str ;
            while ((line = bufferedReader.readLine()) != null){
                /*record count*/
                count++;
                /*Determine if it's needed resize*/
                if (count > maxSize * threshold){
                    return false;
                }
                str = line.split(",");
                /*create a object to store the data*/
                AssetCell assetCell = new AssetCell(Integer.parseInt(str[0]),
                        str[1],str[2],
                        Double.parseDouble(str[3]),Double.parseDouble(str[4]),str[5],str[6],str[7]);
                /*put data into hashtable and calculate the hashcode*/
                put(assetCell.symbol,assetCell,hashCode(assetCell.symbol.hashCode()));
            }
            bufferedReader.close();
            fileReader.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {//close the buffer in finally block
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /*read Trade File*/
    private boolean readTradeFile(File file){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String[] str ;
            while ((line = bufferedReader.readLine()) != null){
                count++;
                if (count > maxSize * threshold){
                    return false;
                }
                str = line.split(",");
                /*create a object to store the data*/
                TradeCell tradeCell = new TradeCell(str[0],
                        str[1],str[2],Double.parseDouble(str[3]),Double.parseDouble(str[4]),Double.parseDouble(str[5]),
                        Double.parseDouble(str[6]),Double.parseDouble(str[7]), Double.parseDouble(str[8]),Double.parseDouble(str[9]),
                        Double.parseDouble(str[10]),Double.parseDouble(str[11]),Double.parseDouble(str[12]),Double.parseDouble(str[13]),
                        Double.parseDouble(str[14]),Double.parseDouble(str[15]), Double.parseDouble(str[16]),Double.parseDouble(str[17]),
                        Double.parseDouble(str[18]),Double.parseDouble(str[19]),Double.parseDouble(str[20]),Double.parseDouble(str[21]),
                        Double.parseDouble(str[22]));
                /*put data into hashtable and calculate the hashcode*/
                put(tradeCell.symbol,tradeCell,hashCode(tradeCell.symbol.hashCode()));
            }
            bufferedReader.close();
            fileReader.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    /*create a table to store asset data*/
    public void createHashTableAsset(File file){
        while (!readAssetFile(file)){
            maxSize *= 3;
            hashPlace = new HashEntry[maxSize];
        }

    }
    /*create a table to store trade data*/
    public void createHashTableTrade(File file){
        while (!readTradeFile(file)){
            maxSize *= 3;
            hashPlace = new HashEntry[maxSize];
        }

    }
    /*put data into hashtable*/
    public void put(String key, Object value, int i){
        if (hashPlace[i] == null){
            hashPlace[i] = new HashEntry(key, value);
            return;
        }
        HashEntry place = hashPlace[i];
        while (place.next != null){
            place = place.next;
        }
        addNext(place,key,value);
    }
    private void addNext(HashEntry entry, String key, Object value){
        entry.next = new HashEntry(key, value);
    }
    /*determine if there are key*/
    public boolean hasKey(String key){
        int index = hashCode(key.hashCode());
        return hashPlace[index] != null;
    }
    /*get the object according key*/
    public Object get(String key){
        if (hasKey(key)){
            HashEntry i = hashPlace[hashCode(key.hashCode())];
            while (!i.key.equals(key) ){
                if (i.next == null){
                    return null;
                }
                i = i.next;
            }
            return i.value;
        }
        return null;
    }

}
