package com.assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DSAGraph {
    public final HashMap<String,DSANode> nodesInHashMap = new HashMap<>();//node
    private final ArrayList<Edge> edges = new ArrayList<>();//edge
    /*inner class for edge*/
    private static class Edge{
        public String start;
        public String end;
    }
    /*get first neighbor*/
    private DSANode getFirstNeighbor(String nodeName){
        DSANode node = nodesInHashMap.get(nodeName);
        if (node.nextNode == null){
            return null;
        }
        return nodesInHashMap.get(node.nextNode.vexName);
    }
    /*get next neighbor*/
    private DSANode getNextNeighbor(String nodeName, DSANode beforeNode){
        DSANode node = nodesInHashMap.get(nodeName);
        while (!node.vexName.equals(beforeNode.vexName)){
            node = node.nextNode;
        }
        if (node.nextNode == null)
            return null;
        return nodesInHashMap.get(node.nextNode.vexName);
    }
    /*deep search first to find all path*/
    private void dfs(String nodeName, String way, String end){
        way = way + "->" + nodesInHashMap.get(nodeName).vexName;
        nodesInHashMap.get(nodeName).IsVisitedDFS = true;//mark the node
        DSANode nextNode = getFirstNeighbor(nodeName);
        while(nextNode != null){
            if (nextNode.vexName.equals(end)){
                System.out.println(way + "->" + nextNode.vexName);
                nextNode = getNextNeighbor(nodeName,nextNode);
            }
            if (nextNode == null)
                break;
            if (!nextNode.IsVisitedDFS)
                dfs(nextNode.vexName,way,end);
            nextNode = getNextNeighbor(nodeName,nextNode);
        }
        nodesInHashMap.get(nodeName).IsVisitedDFS = false;

    }
    /*call dfs function*/
    public void findAllWay(String start, String end){
        dfs(start,"",end);
    }
    /*create a graph*/
    public void creat(DSALinkedList linkedList){
        /*null pointer check*/
        if (linkedList == null){
            System.out.println("please load trade data first");
        }
        /*add edge */
        for (int i = 0; i < linkedList.size(); i++) {
            Edge edge = new Edge();
            edge.start = ((TradeCell)linkedList.get(i)).baseAsset;
            edge.end = ((TradeCell)linkedList.get(i)).quoteAsset;
            edges.add(edge);
        }
        //Generate nodes and prevent them from duplicating
        HashSet<String> hashSet = new HashSet<>();
        for (Edge edge : edges) {
            hashSet.add(edge.start);
            hashSet.add(edge.end);
        }
        for (String s : hashSet){
            DSANode node = new DSANode(s);
            nodesInHashMap.put(s, node);
        }
        //Create an adjacency list
        addRelate();
    }
    private void addRelate(){
        DSANode p1, p2 ;
        for (Edge edge : edges){
            p1 = nodesInHashMap.get(edge.start);
            boolean flag = false;
            while (p1.nextNode != null){
                p1 = p1.nextNode;
                //prevent duplicating
                if (p1.vexName.equals(edge.end)) {
                    flag = true;
                    break;
                }
            }
            if (flag){
                continue;
            }
            p1.nextNode = new DSANode(edge.end);
        }
        for (Edge edge : edges){
            p2 = nodesInHashMap.get(edge.end);
            boolean flag = false;
            while (p2.nextNode != null){
                p2 = p2.nextNode;
                if (p2.vexName.equals(edge.start)) {
                    flag = true;
                    break;
                }
            }
            if (flag){
                continue;
            }
            p2.nextNode = new DSANode(edge.start);
        }
    }
    /*show the adjacency*/
    public void display(){
        for (String s : nodesInHashMap.keySet()){
            System.out.print(s + "->");
            while (nodesInHashMap.get(s).nextNode != null){
                System.out.print(nodesInHashMap.get(s).nextNode.vexName + "->");
                nodesInHashMap.get(s).nextNode = nodesInHashMap.get(s).nextNode.nextNode;
            }
            System.out.print("\b\b\n");
        }
    }
}


class DSANode {
    public boolean IsVisitBFS = false;
    public boolean IsVisitedDFS = false;
    public DSANode nextNode;
    public String vexName;
    DSANode(String name){
        vexName = name;
    }
}
