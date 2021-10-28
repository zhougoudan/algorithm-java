package com.assignment;

public class DSAAdvancedSort {
    /*quick sort*/
    public static void quickSort(double[] arr,int low,int high){
        int i,j;
        double temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        temp = arr[low];

        while (i<j) {
            while (temp>=arr[j]&&i<j) {
                j--;
            }
            while (temp<=arr[i]&&i<j) {
                i++;
            }
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        arr[low] = arr[i];
        arr[i] = temp;
        quickSort(arr, low, j-1);
        quickSort(arr, j+1, high);
    }

}
