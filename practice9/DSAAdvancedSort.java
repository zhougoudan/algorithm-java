package practice9;

import java.util.Arrays;

public class DSAAdvancedSort {
    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        temp = arr[low];

        while (i<j) {
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            while (temp>=arr[i]&&i<j) {
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
    public static void merge(int[] arr,int low,int mid,int high,int[] tmp){
        int i = 0;
        int j = low,k = mid+1;
        while(j <= mid && k <= high){
            if(arr[j] < arr[k]){
                tmp[i++] = arr[j++];
            }else{
                tmp[i++] = arr[k++];
            }
        }
        while(j <= mid){
            tmp[i++] = arr[j++];
        }

        while(k <= high){
            tmp[i++] = arr[k++];
        }

        for(int t=0;t<i;t++){
            arr[low+t] = tmp[t];
        }
    }
    public static void mergeSort(int[] arr,int low,int high,int[] tmp) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid, tmp); //对左边序列进行归并排序
            mergeSort(arr, mid + 1, high, tmp);  //对右边序列进行归并排序
            merge(arr, low, mid, high, tmp);    //合并两个有序序列
        }
    }
    private static int partition3(int[] array,int l,int r){
        int value = array[l];
        int lt = l;
        int i = l+1;
        int gt = r+1;
        while (i < gt){
            if (array[i] < value){
                swap(array,i,lt+1);
                lt++;
                i++;
            }else if(array[i] > value){
                swap(array,i,gt-1);
                gt--;
            }else{
                i++;
            }
        }
        swap(array,l,lt);
        return lt;
    }
    private static void swap(int[] arr, int i, int j) { int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args){
        System.out.println("quickSort:");
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("\nmergeSort:");
        int[] arr1 = {11,44,23,67,88,65,34,48,9,12};
        int[] tmp = new int[arr1.length];
        mergeSort(arr1,0,arr1.length-1,tmp);
        for(int i=0;i<arr1.length;i++){
            System.out.print(arr1[i]+" ");
        }

        System.out.println("\nquickSort3way:");
        int[] arr2 = {11,44,23,67,88,65,34,48,9,12};
        partition3(arr2,1,9);
        for(int i=0;i<arr2.length;i++){
            System.out.print(arr1[i]+" ");
        }
    }
}
