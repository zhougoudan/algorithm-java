package com.assignment;

import java.io.File;
import java.util.Scanner;

public class Main {

    /*menu*/
    static public void menu(){
        System.out.println("\n\nEnter a number to make a choice: \n" +
                "1 \t Load data \n" +
                "2 \t Find and display asset details\n" +
                "3 \t Find and display trade details\n" +
                "4 \t Find and display potential trade paths \n" +
                "5 \t Set asset filter\n" +
                "6 \t Asset overview\n"+
                "7 \t Trade overview\n"+
                "8 \t Save data\n"+
                "9 \t Exit\n");
    }


    /*search asset data*/
    static public void searchAssetData(DSAHashtable assetTable){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the symbol that you want to search");
        String symbol = scanner.nextLine();
        /*check null pointer*/
        if (assetTable == null){
            System.out.println("no data");
            return;
        }
        /*search symbol in hashtable*/
        AssetCell assetCell = (AssetCell) assetTable.get(symbol);
        /*check null pointer*/
        if (assetCell == null){
            System.out.println("This symbol does not exist");
            return;
        }
        System.out.println("\nHere are the details:");
        /*show*/
        assetCell.show();

    }
    /*search trade data*/
    static public void searchTradeData(DSAHashtable tradeTable){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the symbol that you want to search");
        String trade = scanner.nextLine();
        /*check null pointer*/
        if (tradeTable == null){
            System.out.println("no data");
            return;
        }
        /*search symbol in hashtable*/
        TradeCell tradeCell = (TradeCell) tradeTable.get(trade);
        /*check null pointer*/
        if (tradeCell == null){
            System.out.println("This symbol does not exist");
            return;
        }
        System.out.println("\nHere are the details:");
        tradeCell.show();
    }
    /*trade statics*/
    static public void tradeStatics(DSALinkedList tradeList){
        /*check null pointer*/
        if (tradeList == null){
            System.out.println("please load trade data first");
            return;
        }
        int length = tradeList.size();
        /*creat arrays for sort*/
        double[] count = new double[length];
        double[] price = new double[length];
        double[] volume = new double[length];
        /*transfer data in tradeList to array*/
        for (int i = 0; i < length; i++) {
            count[i] = ((TradeCell)tradeList.get(i)).count;
            price[i] = ((TradeCell)tradeList.get(i)).weightedAvgPrice;
            volume[i] = ((TradeCell)tradeList.get(i)).volume;
        }
        /*sort*/
        DSAAdvancedSort.quickSort(count,0,length - 1);
        DSAAdvancedSort.quickSort(price,0,length - 1);
        DSAAdvancedSort.quickSort(volume,0,length - 1);
        /*statics*/
        System.out.println("\n\ntop 10 volume");
        for (int i = 0; i < 10; i++) {
            System.out.println(volume[i]);
        }
        System.out.println("\n\ntop 10 price");
        for (int i = 0; i < 10; i++) {
            System.out.println(price[i]);
        }
        System.out.println("\n\ntop 10 count");
        for (int i = 0; i < 10; i++) {
            System.out.println(count[i]);
        }
    }

    static public void assetStatics(DSALinkedList assetList){
        /*check null pointer*/
        if (assetList == null){
            System.out.println("please load asset data first");
            return;
        }
        /*to get average*/
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < assetList.size(); i++) {
            sum1 += ((AssetCell)assetList.get(i)).markeyCapCleaned;
            sum2 += ((AssetCell)assetList.get(i)).circulating;
        }
        System.out.println("the average market is "  +   sum1/assetList.size() );
        System.out.println("the average circulating is " + sum2/assetList.size());

    }
    /*creat a graph from tradeList*/
    static public void findAllWay(DSALinkedList tradeList){
        /*check null pointer*/
        if (tradeList == null){
            System.out.println("please load trade data first");
            return;
        }
        /*input start and end*/
        System.out.println("please input the start");
        Scanner scanner = new Scanner(System.in);
        String start = scanner.nextLine();
        System.out.println("please input the end");
        String end = scanner.nextLine();
        /*create graph*/
        DSAGraph graph = new DSAGraph();
        graph.creat(tradeList);
        /*find all path*/
        graph.findAllWay(start,end);
    }
    /*set filter*/
    static public void filter(DSALinkedList assetList,DSALinkedList tradeList){
        /*check null pointer*/
        if (tradeList == null){
            System.out.println("please load trade data first");
            return;
        }
        /*input filter*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input symbol of the filter ");
        String filter = scanner.nextLine();
        System.out.println("Here is the data that filter deleted in the trade ");
        int countTrade = 0;
        /*delete data from tradelist*/
        for (int i = 0; i < tradeList.size(); i++) {
            if (filter.equals(((TradeCell)tradeList.get(i)).quoteAsset) || filter.equals(((TradeCell)tradeList.get(i)).baseAsset)){
                TradeCell t = (TradeCell)tradeList.remove(i);
                System.out.println(t.symbol);
                countTrade++;
                i = i - 1;
            }
        }
        System.out.println("A total of " + countTrade + " pieces of trade data were deleted");
        System.out.println("Here is the data that filter deleted in the asset ");
        int countAsset = 0;
        /*delete data from assetlist*/
        for (int i = 0; i < assetList.size(); i++) {
            if (filter.equals(((AssetCell)assetList.get(i)).symbol)){
                AssetCell a = (AssetCell) assetList.remove(i);
                System.out.println(a.symbol);
                countAsset++;
                i = i - 1;
            }
        }
        System.out.println("A total of " + countAsset + " pieces of data asset were deleted");
    }

    public static void main(String[] args) {
        /*if no argument*/
        if (args.length == 0){
            System.out.println("Use \"cryptoGraph -i\" to enter interactive testing mode");
            System.out.println("Use \"cryptoGraph -r <assetFile> <tradeFile>\" to enter report mode");
            return;
        }
        /*if starts in interactive mode*/
        if (args[0].equals("-i")){

            Scanner scanner = new Scanner(System.in);
            boolean flag = true;
            while (flag) {
                System.out.println("\n\nEnter a number to make a test: \n" +
                    "1 \t test linkedList \n" +
                    "2 \t test Graph\n" +
                    "3 \t test sort\n" +
                    "4 \t testHashtable \n"+
                    "5 \t exit");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        UnitTestDSALinkedList.test();
                        break;
                    case 2:
                        UnitTestDSAGraph.test();
                        break;
                    case 3:
                        UnitTestAdvancedSort.test();
                        break;
                    case 4:
                        UnitTestDSAHashtable.test();
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        break;
                }
            }
        return;
        }
        /*if starts in report mode*/
        if (args[0].equals("-r")){
            /*file path*/
            String assetFile = args[1];
            String tradeFile = args[2];
            /*serializable file location*/
            String assetListSerFile = "assetList";
            String tradeListSerFile = "tradeList";
            String assetTableSerFile = "assetTable";
            String tradeTableSerFile = "tradeTable";
            /*create data structure to store data*/
            DSALinkedList assetList = null;
            DSALinkedList tradeList = null;
            DSAHashtable assetTable = null;
            DSAHashtable tradeTable = null;
            Scanner scanner = new Scanner(System.in);
            boolean flag = true;
            while (flag){
                menu();
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("enter 1  load asset data, 2 load trade data , 3 iload serialised data");
                        int k = scanner.nextInt();
                        switch (k) {
                            case 1:
                                /*load asset data*/
                                assetList = FileOp.readAssetFile(assetFile);
                                assetTable = new DSAHashtable(5, 5);
                                assetTable.createHashTableAsset(new File(assetFile));
                                System.out.println("successful!!");
                                break;
                            case 2:
                                /*load trade data*/
                                tradeList = FileOp.readTradeFile(tradeFile);
                                tradeTable = new DSAHashtable(5, 5);
                                tradeTable.createHashTableTrade(new File(tradeFile));
                                System.out.println("successful!!");
                                break;
                            case 3:
                                /*load trade and asset data form serializable*/
                                assetList = FileOp.readSerFileList(assetListSerFile);
                                tradeList = FileOp.readSerFileList(tradeListSerFile);
                                assetTable = FileOp.readSerFileTable(assetTableSerFile);
                                tradeTable = FileOp.readSerFileTable(tradeTableSerFile);
                                System.out.println("load successful");
                                break;

                        }
                        break;
                    case 2:
                        searchAssetData(assetTable);
                        break;
                    case 3:
                        searchTradeData(tradeTable);
                        break;
                    case 4:
                        findAllWay(tradeList);
                        break;
                    case 5:
                        filter(assetList,tradeList);
                        break;
                    case 6:
                        assetStatics(assetList);
                        break;
                    case 7:
                        tradeStatics(tradeList);
                        break;
                    case 8:
                        /*save data structure to serialization file*/
                        if (tradeList != null) {
                            FileOp.saveSerFileList(tradeListSerFile, tradeList);
                            System.out.println("trade List save successful");
                        } else {
                            System.out.println("There is no trade information to store");
                        }

                        if (assetTable != null) {
                            FileOp.saveSerFileTable(assetTableSerFile, assetTable);
                            System.out.println("asset table save successful");
                        } else {
                            System.out.println("There is no asset information to store");
                        }
                        if (tradeTable != null) {
                            FileOp.saveSerFileTable(tradeTableSerFile, assetTable);
                            System.out.println("trade table save successful");
                        } else {
                            System.out.println("There is no trade information to store");
                        }
                        if (assetList != null) {
                            FileOp.saveSerFileList(assetListSerFile, assetList);
                            System.out.println("asset List save successful");
                        } else {
                            System.out.println("There is no asset information to store");
                        }
                        break;
                    case 9:
                        flag = false;
                        break;
                    default:
                        break;

                }
            }
        }

    }
}
