package com.assignment;

import java.util.Scanner;

public class UnitTestDSALinkedList {
    /*test linked list*/
    public static void test(){
        Scanner scanner = new Scanner(System.in);
        DSALinkedList list = new DSALinkedList();
        System.out.println("Enter the integer you wish to insert into the list and enter -1 to exit");
        int num = scanner.nextInt();
        while (num != -1){
            list.add(num);
            num = scanner.nextInt();
        }
        System.out.println("The inside of the linked list is as follows");
        list.showList();
        System.out.println("Enter how many numbers do you want to delete");
        num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            list.remove(0);
        }
        System.out.println("The inside of the linked list is as follows");
        list.showList();
    }
}
