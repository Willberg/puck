package algorithm.mine.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySort {
    private int[] arr;
    private int[] oriArr;

    public MySort(int m, int rand, int n) {
        this.oriArr = generateRandArray(m, rand, n);
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
     * gap是每组元素之间的间隔值,最开始间隔为原数组的一半,因此每组元素只有两个
     */
    public void shellSort() {
        for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 从gap开始的每一个元素都要进行插入排序
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
        buildMaxHeap(len);

        while (len > 0) {
            swap(0, len - 1);
            len--;
            toMaxHeap(0, len);
        }
    }

    private void buildMaxHeap(int len) {
        for (int i = len >> 1; i >= 0; i--) {
            toMaxHeap(i, len);
        }
    }

    // 大顶堆化
    private void toMaxHeap(int p, int len) {
        while (p < len) {
            int large = p;
            // 取树结构的左儿子节点
            int l = 2 * p + 1;
            if (l < len && arr[l] > arr[large]) {
                large = l;
            }

            if (l + 1 < len && arr[l + 1] > arr[large]) {
                large = l + 1;
            }
            if (p == large) {
                break;
            }
            swap(p, large);
            p = large;
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

    /**
     * 桶排序
     * 确定桶的数量（max-min）/length + 1，将数据分布到各个桶中，分别对桶中元素排序，最后将元素复制到原数组中，适合最大值和最小值相差大的数组排序
     */
    public void bucketSort() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        List<Integer> arrList = new ArrayList<>(arr.length);
        for (int v : arr) {
            arrList.add(v);
            max = Math.max(max, v);
            min = Math.min(min, v);
        }

        int bucketNum = (max - min) / arr.length + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int v : arr) {
            int idx = (v - min) / arr.length;
            buckets.get(idx).add(v);
        }

        // 对桶内元素进行排序
        int j = 0;
        for (int i = 0; i < buckets.size(); i++) {
            List<Integer> list = buckets.get(i);
            if (list != null) {
                insertSort(list);
                for (int v : list) {
                    // 将桶内元素放回原数组
                    arr[j++] = v;
                }
            }
        }
    }

    private void insertSort(List<Integer> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.get(j) < list.get(j - 1)) {
                    swap(list, j - 1, j);
                }
            }
        }
    }

    private void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    /**
     * 基数排序
     * 获取最大位数的位数，从低位到高位依次取每个位作为基数数组，进行计数排序(适用于非负数)
     */
    public void radixSort() {
        int max = Integer.MIN_VALUE;
        for (int v : arr) {
            max = Math.max(v, max);
        }

        List<List<Integer>> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList<>());
        }

        int dev = 1;
        int mod = 10;
        for (int i = max; i > 0; i /= 10, dev *= 10) {
            for (int v : arr) {
                int idx = (v / dev) % mod;
                list.get(idx).add(v);
            }

            int pos = 0;
            for (int j = 0; j < list.size(); j++) {
                for (int k = 0; k < list.get(j).size(); k++) {
                    arr[pos++] = list.get(j).get(k);
                }
            }
        }
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

    /**
     * 生成随机数
     *
     * @param m    随机数个数
     * @param rand 随机数范围
     * @param n    随机数正数比例
     * @return 随机数数组
     */
    public int[] generateRandArray(int m, int rand, int n) {
        int[] retArr = new int[m];
        n = Math.abs(n);
        Random random = new Random();
        int i = 0;
        while (i < m) {
            // 随机符号
            int sign = random.nextInt(rand) % n == 0 ? 1 : -1;
            retArr[i++] = random.nextInt(rand) * sign;
        }

        return retArr;
    }

    public static void main(String[] args) {
        int m = 20, rand = 10, n = 2;
        MySort mySort = new MySort(m, rand, n);
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

        mySort.setArr(arr);
        mySort.bucketSort();
        mySort.printArr(false);

        int[] radixArr = mySort.generateRandArray(m, rand, 1);
        mySort.setArr(radixArr);
        mySort.radixSort();
        mySort.printArr(true);
        mySort.printArr(false);
    }
}
