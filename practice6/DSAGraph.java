package practice6;

import java.io.*;
import java.util.*;

public class DSAGraph {
    public final HashMap<String,DSANode> nodesInHashMap = new HashMap<>();
    private final ArrayList<Edge> edges = new ArrayList<>();
    private static class Edge{
        public String start;
        public String end;
    }
    public void importGraph(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] data ;
            Edge edge = new Edge();
            data = line.split(" ");
            edge.end = data[1];
            edge.start = data[0];
            edges.add(edge);
        }
        bufferedReader.close();
        fileReader.close();
    }
    public void creat(){
        HashSet<String> hashSet = new HashSet<>();
        for (Edge edge : edges) {
            hashSet.add(edge.start);
            hashSet.add(edge.end);
        }
        for (String s : hashSet){
            DSANode node = new DSANode(s);
            nodesInHashMap.put(s, node);
        }
        addRelate();
    }
    private void addRelate(){
        DSANode p1, p2 ;
        for (Edge edge : edges){
            p1 = nodesInHashMap.get(edge.start);
            while (p1.nextNode != null)
                p1 = p1.nextNode;
            p1.nextNode = new DSANode(edge.end);

            p2 = nodesInHashMap.get(edge.end);
            while (p2.nextNode != null)
                p2 = p2.nextNode;
            p2.nextNode = new DSANode(edge.start);

        }
    }
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
    public static void main(String[] args) throws IOException {
        DSAGraph graph = new DSAGraph();
        graph.importGraph(new File("D:\\prac6_1.al"));
        graph.creat();
        graph.display();

        System.out.println("\nthe second graph is:\n");

        DSAGraph graph1 = new DSAGraph();
        graph1.importGraph(new File("D:\\prac6_2.al"));
        graph1.creat();
        graph1.display();
    }
}
