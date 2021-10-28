package com.assignment;

import java.io.File;
import java.util.Scanner;

public class UnitTestDSAHashtable {
    /*test hashtable*/
    public static void test(){
        System.out.println("input asset full filename");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        DSAHashtable assetTable = new DSAHashtable(5,5);
        assetTable.createHashTableAsset(new File(filename));
        System.out.println("please enter the symbol that you want to search");
        String symbol = scanner.nextLine();
        AssetCell assetCell = (AssetCell) assetTable.get(symbol);
        if (assetCell == null){
            System.out.println("This symbol does not exist");
            return;
        }
        System.out.println("\nHere are the details:");
        assetCell.show();
    }
}
