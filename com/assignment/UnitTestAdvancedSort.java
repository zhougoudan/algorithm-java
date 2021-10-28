package com.assignment;

import java.util.Arrays;
import java.util.Scanner;

public class UnitTestAdvancedSort {
    /*test quick sort*/
    public static void test(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many numbers do you want to sort?");
        int length = scanner.nextInt();
        double[] array = new double[length];
        System.out.println("Enter the numbers you wish to sort");
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextDouble();
        }
        DSAAdvancedSort.quickSort(array,0,length-1);
        System.out.println("the sort result:");
        System.out.println(Arrays.toString(array));
    }
}
