package practice8;

import java.io.*;

public class DSAHeap {
    private final DSAHeapEntry[] heapEntries;
    private int size = 0;

    private static class DSAHeapEntry {
        int priority;
        Object value;

        public DSAHeapEntry(int priority, Object value) {
            this.priority = priority;
            this.value = value;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public int getPriority() {
            return priority;
        }

        public Object getValue() {
            return value;
        }
    }

    DSAHeap(int maxSize) {
        heapEntries = new DSAHeapEntry[maxSize];
    }

    public void remove() {
        DSAHeapEntry temp = heapEntries[0];
        swap(0, size - 1);
        size--;
        trickleDown();
    }

    public void add(int priority, Object value) {
        heapEntries[size] = new DSAHeapEntry(priority, value);
        size++;
        trickleUp();
    }

    public void readCSVFile(String filename) throws IOException {
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            add(Integer.parseInt(line.split(",")[0]), line.split(",")[1]);
        }
        bufferedReader.close();
        fileReader.close();
    }

    public void heapSort() {
        int tempSize = size;
        for (int i = 0; i < tempSize; i++) {
            remove();
        }
        size = tempSize;
    }

    public void trickleUp() {
        for (int i = size - 1; i > 0; i = (i - 1) / 2){
            int parent = (i - 1) / 2;
            if (heapEntries[i].priority > heapEntries[parent].priority) {
                swap(i, parent);
            }
        }
    }

    public void swap(int a, int b) {
        DSAHeapEntry c;
        c = heapEntries[a];
        heapEntries[a] = heapEntries[b];
        heapEntries[b] = c;
    }

    public void trickleDown() {
        int child;
        for (int i = 0; i < size / 2; i = child) {
            child = 2 * i + 1;
            if (child < size - 1 && heapEntries[child].priority < heapEntries[child + 1].priority) {
                child = child + 1;
            }
            if (heapEntries[i].priority < heapEntries[child].priority)
                swap(child, i);
        }
    }

    public static void main(String[] args) throws IOException {
        DSAHeap heap = new DSAHeap(7000);
        heap.readCSVFile("D:\\RandomNames7000.csv");
        heap.heapSort();
        for (int i = 0; i < heap.size; i++) {
            System.out.println(heap.heapEntries[i].priority + " " + heap.heapEntries[i].value);
        }

    }
}
