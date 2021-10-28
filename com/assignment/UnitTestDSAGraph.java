package com.assignment;

import java.util.Scanner;

public class UnitTestDSAGraph {
    /*test graph*/
    public static void test(){
        System.out.println("input trade full filename");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        DSALinkedList list = FileOp.readTradeFile(filename);
        DSAGraph graph = new DSAGraph();
        graph.creat(list);
        System.out.println("The graph has the following adjacency list form");
        graph.display();
    }
}
