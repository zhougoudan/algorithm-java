package practice4;

import java.io.*;
import java.util.Scanner;

public class DSABinarySearchTree implements Serializable{
    private TreeNode root = null;
    private String writer = "";
    private class TreeNode implements Serializable{ // Put inside Tree class
        private String m_key;
        private Object m_value;
        private TreeNode m_leftChild;
        private TreeNode m_rightChild;
        public TreeNode(String inKey, Object inVal) {
            if (inKey == null)
                throw new IllegalArgumentException("Key cannot be null");
            m_key = inKey; //consider ¡°owning¡± the key
            m_value = inVal;
            m_rightChild = null;
            m_leftChild = null;
        }
        public String getKey() { return m_key; }
        public Object getValue() { return m_value; }
        public TreeNode getLeft() { return m_leftChild; }
        public void setLeft(TreeNode newLeft) { m_leftChild = newLeft; }
        public TreeNode getRight() { return m_rightChild; }
        public void setRight(TreeNode newRight) { m_rightChild = newRight; }
    }
    public TreeNode add(TreeNode node, TreeNode now){
        if (now == null){
            return node;
        }

        if (Integer.parseInt(node.m_key) >= Integer.parseInt(now.m_key)){
            now.m_rightChild = add(node , now.m_rightChild);
        }
        if (Integer.parseInt(node.m_key) < Integer.parseInt(now.m_key)){
            now.m_leftChild = add(node , now.m_leftChild);
        }
        return now;
    }
    public void createTree() throws IOException {
        FileReader fileReader = new FileReader(new File("C:\\input.csv") );
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String[] item;
        String line;
        while ((line = bufferedReader.readLine()) != null){
            item = line.split(",");
            TreeNode node = new TreeNode(item[0], item[1]);
            root = add(node,root);
        }
        bufferedReader.close();
        fileReader.close();
    }
    public DSABinarySearchTree readFile(String fileName) throws Exception {
        File file = new File(fileName);
        DSABinarySearchTree tree ;
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oo = new ObjectInputStream(fi);
        tree = (DSABinarySearchTree) oo.readObject();
        oo.close();
        fi.close();
        return tree;
    }
    public void saveTree(String fileName) throws Exception{
        File file = new File(fileName);
        FileOutputStream fi = new FileOutputStream(file);
        ObjectOutputStream oo = new ObjectOutputStream(fi);
        oo.writeObject(this);
        oo.close();
        fi.close();

    }
    public void displayTree(TreeNode node){
        if (node == null)
            return;
        displayTree(node.m_leftChild);
        displayNode(node);
        displayTree(node.m_rightChild);
    }
    public void displayNode(TreeNode node){
        System.out.println(node.getKey() + " " + node.getValue().toString());
    }
    public void saveInorder(TreeNode node){
        if (node == null)
            return;
        writeCSVLine(node);
        saveInorder(node.m_leftChild);
        saveInorder(node.m_rightChild);
    }
    public void savePreorder(TreeNode node){
        if (node == null)
            return;
        savePreorder(node.m_leftChild);
        writeCSVLine(node);
        savePreorder(node.m_rightChild);
    }
    public void savePostOrder(TreeNode node){
        if (node == null)
            return;
        savePostOrder(node.m_leftChild);
        savePostOrder(node.m_rightChild);
        writeCSVLine(node);
    }
    public void writeCSVLine(TreeNode node){
        writer = String.join(writer,node.m_key+","+node.m_value+"\n");
    }
    public String findMax(TreeNode node){
        if (node.m_rightChild == null)
            return node.m_key;
        String s = findMax(node.m_rightChild);
        return s;
    }
    public String findMin(TreeNode node){
        if (node.m_leftChild == null)
            return node.m_key;
        String s = findMin(node.m_leftChild);
        return s;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("these menu system options provided : \n" +
                "1,\t\tread a CSV file \n" +
                "2,\t\tread a serialized file \n" +
                "3,\t\tdisplay the tree pre-order\n" +
                "4,\t\twrite a CSV file \n" +
                "5,\t\twrite a serialized file\n" +
                "6,\t\tshow the min and max key\n" +
                "7,\t\texit\n");
        Scanner scanner = new Scanner(System.in);
        DSABinarySearchTree tree = new DSABinarySearchTree();
        int flag = 1;
        while (flag == 1){
            int k = scanner.nextInt();
            switch (k){
                case 1:
                    tree.createTree();
                    break;
                case 2:
                    tree = tree.readFile("D:\\tree");
                    break;
                case 3:
                    tree.displayTree(tree.root);
                    break;
                case 4:
                    System.out.println("type 1 is inorder, 2 is preorder or 3 postOrder traversal");
                    int j = scanner.nextInt();
                    switch (j){
                        case 1:
                            tree.saveInorder(tree.root);
                            break;
                        case 2:
                            tree.savePreorder(tree.root);
                            break;
                        case 3:
                            tree.savePostOrder(tree.root);
                            break;
                    }
                    FileWriter fileWriter = new FileWriter(new File("D:\\output.csv"));
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(tree.writer);
                    break;
                case 5:
                    tree.saveTree("D:\\tree");
                    break;
                case 6:
                    System.out.println("the max key is " + tree.findMax(tree.root));
                    System.out.println("the min key is " + tree.findMin(tree.root) );
                case 7:
                    flag = 0;
                    break;
                default:
                    break;

            }
        }
    }
}
