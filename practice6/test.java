package practice6;


import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class test {
    @Test
    public void testGraph() throws IOException {
        DSAGraph graph = new DSAGraph();
        graph.importGraph(new File("D:\\prac6_1.al"));
        graph.creat();
        DSANode nextNode = graph.nodesInHashMap.get("A").nextNode;
        Assert.assertEquals(nextNode.vexName,"B");
    }
}
