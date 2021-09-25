package algorithm.mine.sort.heap;

public class MaxHeap {
    private int[] arr;

    public MaxHeap(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        int len = arr.length;
        toHeap(len);

        while (len > 0) {
            swap(0, len - 1);
            len--;
            toHeap(len);
        }
    }

    private void toHeap(int len) {
        int i = (len >> 1) - 1;
        while (i >= 0) {
            int lc = i * 2 + 1;
            if (lc + 1 < len && arr[lc] < arr[lc + 1]) {
                lc++;
            }

            if (arr[lc] > arr[i]) {
                swap(i, lc);
            }
            i--;
        }
    }

    private void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 6, 2, 0, 7, 9, 20, 11, 24, 6, 2, 1, 4542, 5};
        MaxHeap heap = new MaxHeap(arr);
        heap.sort();
        for (int v : arr) {
            System.out.print(v + ",");
        }
    }
}
