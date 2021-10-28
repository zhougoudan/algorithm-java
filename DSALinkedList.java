package com.assignment;

import java.io.Serializable;

class Node implements Serializable{
    public Object e;
    public Node next;
    public Node pre;
    public Node(){

    }
    public Node(Object e){
        this.e = e;
        next = null;
        pre = null;
    }
}

public class DSALinkedList implements Serializable {
    private Node head;
    private Node tail;
    private int size = 0;

    public DSALinkedList() {
        head = new Node();
        tail = new Node();
        head.next =null;
        tail.pre = null;
    }
    /*justify if the linkedlist is empty*/
    private boolean empty() {
        if (head.next == null)
            return true;
        return false;
    }
    /*find pre node in index*/
    private Node findpre(int index){
        Node rnode = head;
        int dex = -1;
        while(rnode.next != null){
            if( dex== index - 1){
                return rnode;
            }
            rnode = rnode.next;
            dex++;
        }
        return null;
    }
    /*return a node located in index*/
    private Node findthis(int index){
        Node rnode = head;
        int dex = -1;
        while(rnode.next != null){
            if(dex == index)
                return rnode;
            rnode = rnode.next;
            dex++;
        }
        if(dex == size - 1){
            return rnode;
        }
        return null;
    }
    /*add a node to the list tail*/
    public void add(Object e) {
        Node node = new Node(e);
        Node rnode = head;
        if (this.empty()) {
            rnode.next = node;
            rnode.next.pre = null;
            tail.pre = node;
            size++;
        } else {
            while (rnode.next != null)
                rnode = rnode.next;
            rnode.next = node;
            node.pre = rnode;
            tail.pre = node;
            size++;
        }
    }
    /*remove node located in index*/
    public Object remove(int index){
        Object ob= this.get(index);
        if(index <0 || index >= size)
            return null;
        if(index == size - 1){
            Node prenode = this.findpre(index);
            this.tail.pre = this.tail.pre.pre;
            this.tail.pre.next.pre = null;
            this.tail.pre.next =null;
            size--;
            return ob;
        }
        else{
            Node prenode = this.findpre(index);
            prenode.next = prenode.next.next;
            prenode.next.pre.next = null;
            prenode.next.pre = prenode.next.pre.pre;
            size--;
            return ob;
        }
    }
    /*show linked list*/
    public void showList(){
        Node p = head.next;
        while (p != null){
            System.out.print(p.e.toString() + "->");
            p=p.next;
        }
        System.out.println();
    }
    /*get object form index*/
    public Object get(int index){
        Node thisnode = this.findthis(index);
        return thisnode.e;
    }
    /*return size of linked list*/
    public int size(){
        return size;
    }
}
