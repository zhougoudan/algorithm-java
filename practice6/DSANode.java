package practice6;

public class DSANode {
    public boolean IsVisitBFS = false;
    public boolean IsVisitedDFS = false;
    public DSANode nextNode;
    public String vexName;
    DSANode(String name){
        vexName = name;
    }
}
