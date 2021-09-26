package algorithm.mine.sort;

import java.util.Random;

public class MySort {
    private int[] arr;
    private int[] oriArr;

    public MySort(int n, int rand) {
        this.oriArr = generateRandArray(n, rand);
        this.arr = deepCopy(this.oriArr);
    }

    public MySort(int[] arr) {
        this.oriArr = arr;
        this.arr = deepCopy(arr);
    }

    public int[] getOriArr() {
        return this.oriArr;
    }

    public void setArr(int[] arr) {
        this.oriArr = arr;
        this.arr = deepCopy(arr);
    }

    /**
     * 冒泡排序
     * 每次将除末尾已排序的元素外的最大(小)元素放到末尾
     */
    public void bubbleSort() {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     * 每次将除开头已排序的元素外的最小(大)元素放到开头
     */
    public void selectSort() {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(min, i);
        }
    }

    /**
     * 插入排序
     * 每次将一个元素与开头已排序的每个元素比较,放置在合适位置
     */
    public void insertSort() {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(j - 1, j);
                }
            }
        }
    }

    /**
     * shell排序
     * 通过对数组依次按gap分组进行插入排序使数组有序, gap初值为数组长度的一半,直到为1
     */
    public void shellSort() {
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j - gap >= 0; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(j, j - gap);
                    }
                }
            }
        }
    }

    /**
     * 归并排序
     * 先对无序数组分治处理,然后将两个有序数组合并
     */
    public void mergeSort(int l, int r, int[] tmp) {
        if (l + 1 >= r) {
            return;
        }

        // divide(分治)
        int mid = l + (r - l) / 2;
        // 不包括mid
        mergeSort(l, mid, tmp);
        mergeSort(mid, r, tmp);

        // 合并
        int p = l, q = mid, i = l;
        while (p < mid || q < r) {
            if (q >= r || (p < mid && arr[p] < arr[q])) {
                tmp[i++] = arr[p++];
            } else {
                tmp[i++] = arr[q++];
            }
        }

        for (i = l; i < r; i++) {
            arr[i] = tmp[i];
        }
    }

    /**
     * 快速排序
     * 取数组第一个元素作为基准,将小于基准的值放在左边,大于基准的值放在右边,然后将左右两边的元素递归,进行同样的处理
     */
    public void quickSort(int l, int r) {
        if (l >= r) {
            return;
        }

        int pivot = arr[l];
        int i = l, j = r - 1;
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }

            // arr[i]作为pivot已经被保存
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot) {
                i++;
            }

            // 在上面arr[i]中保存了arr[j]
            arr[j] = arr[i];
        }

        // 将pivot放回i位置
        arr[i] = pivot;
        quickSort(l, i);
        quickSort(i + 1, r);
    }

    /**
     * 堆排序
     * 现将数组大(小)顶堆化,然后依次从堆顶取值跟末尾元素交换,之后再堆化
     */
    public void heapSort() {
        int len = arr.length;
        toHeap(len);

        while (len > 0) {
            swap(0, len - 1);
            len--;
            toHeap(len);
        }
    }

    // 堆化
    private void toHeap(int len) {
        // 取树结构的父节点
        int i = (len >> 1) - 1;
        while (i >= 0) {
            int j = 2 * i + 1;
            if (j + 1 < len && arr[j] < arr[j + 1]) {
                j++;
            }

            if (arr[j] > arr[i]) {
                swap(i, j);
            }
            i--;
        }
    }

    /**
     * 计数排序
     * 找出数组中的最值,生成一个数量为(最大值-最小值)的数组,该数组的索引表示原数组(元素值-最小值),该数组的值表示原数组元素的次数,注意符号
     */
    public void countSort() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int v : arr) {
            max = Math.max(max, v);
            min = Math.min(min, v);
        }

        int[] count = new int[max - min + 1];
        for (int v : arr) {
            count[v - min]++;
        }

        int j = 0;
        for (int i = 0; i < count.length; i++) {
            int c = count[i];
            for (int k = 0; k < c; k++) {
                arr[j++] = i + min;
            }
        }
    }

    // 桶排序
    public void bucketSort() {

    }

    // 基数排序
    public void radixSort() {

    }

    public void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void printArr(boolean isOrig) {
        int[] arr = this.arr;
        if (isOrig) {
            arr = this.oriArr;
        }

        StringBuilder sb = new StringBuilder();
        for (int v : arr) {
            sb.append(v).append(",");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public int[] deepCopy(int[] arr) {
        int[] retArr = new int[arr.length];
        int i = 0;
        for (int v : arr) {
            retArr[i++] = v;
        }
        return retArr;
    }

    public int[] generateRandArray(int n, int rand) {
        int[] retArr = new int[n];
        Random random = new Random();
        int i = 0;
        while (i < n) {
            // 随机符号
            int sign = random.nextInt(rand) % 2 == 0 ? 1 : -1;
            retArr[i++] = random.nextInt(rand) * sign;
        }

        return retArr;
    }

    public static void main(String[] args) {
        MySort mySort = new MySort(30, 10);
//        int[] arr = {1, 6, 2, 0, 7, 9, 20, 11, 24, 6, 2, 1, 4542, 5};
        mySort.printArr(true);

        int[] arr = mySort.getOriArr();
        mySort.setArr(arr);
        mySort.bubbleSort();
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.selectSort();
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.insertSort();
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.shellSort();
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.mergeSort(0, arr.length, new int[arr.length]);
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.quickSort(0, arr.length);
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.heapSort();
        mySort.printArr(false);

        mySort.setArr(arr);
        mySort.countSort();
        mySort.printArr(false);

    }
}
