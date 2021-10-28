package practice7;

import java.io.*;

public class DSAHashtable {
    private HashEntry[] hashPlace;
    private final float threshold;
    private int count = 0;
    private int maxSize;
    private static class HashEntry {
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
    private int hashCode(int num){
        return num % (maxSize - 7);
    }
    DSAHashtable(int threshold,int maxSize){
        this.maxSize = maxSize;
        this.threshold = threshold;
        hashPlace = new HashEntry[maxSize];
    }
    private boolean readCSVFile(File file){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line ;
            String[] data ;
            while ((line = bufferedReader.readLine()) != null){
                count++;
                if (count > maxSize * threshold){
                    return false;
                }
                data = line.split(",");
                put(data[0],data[1],hashCode(Integer.decode(data[0])));
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
    public void createHashTable(File file){
        while (!readCSVFile(file)){
            maxSize *= 3;
            hashPlace = new HashEntry[maxSize];
        }

    }
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
    public boolean hasKey(String key){
        int index = hashCode(Integer.decode(key));
        return hashPlace[index] != null;
    }
    public Object get(String key){
        if (hasKey(key)){
            HashEntry i = hashPlace[hashCode(Integer.parseInt(key))];
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
    private HashEntry find(String key){
        int i = hashCode(Integer.parseInt(key));
        if (hasKey(key)){
            HashEntry index = hashPlace[i];
            while (!index.next.key.equals(key) ){
                if (index.next.next == null){
                    return null;
                }
                index = index.next;
            }
            return index;
        }
        return null;
    }
    private HashEntry fin(String key){
        int i = hashCode(Integer.parseInt(key));
        if (hasKey(key)){
            HashEntry index = hashPlace[i];
            if(index.key.equals(key)){
                return index;
            }
        }
        return null;
    }
    public HashEntry remove(String key){
        HashEntry node = find(key);
        if (node == null){
            node = fin(key);
            return node;
        }
        HashEntry removed;
        if (node != null){
            removed = node.next;
            node.next = node.next.next;
            return removed;
        }
        return null;
    }
    public static void main(String[] args) {
        DSAHashtable hashtable = new DSAHashtable(10,12);
        hashtable.createHashTable(new File("D:\\RandomNames7000.csv"));
        String result = (String) hashtable.get("14495655");
        System.out.println(result);
        HashEntry removed = hashtable.remove("14495655");
        if (removed == null){
            System.out.println("no thing removed");
            return;
        }
        System.out.println("removed node`s key is "+ removed.key + " name is: " + (String)removed.value);
    }
}
