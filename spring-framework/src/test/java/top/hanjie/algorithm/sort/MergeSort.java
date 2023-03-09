package top.hanjie.algorithm.sort;

/**
 * 归并排序
 *
 * @author 黄汉杰
 * <p>描述：归并排序<p>
 */
public class MergeSort {

    /**
     * 用于辅助排序的空间
     */
    private static Comparable[] aux;

    /**
     * 归并排序：
     * 现将所有元素都拷贝到辅助空间 aux 中，然后在归并回原来的数组 array 中，归并的 4 个判断条件：
     * 1.左半边的元素用尽了（取右半边的元素）
     * 2.右半边的元素用尽了（取左半边的元素）
     * 3.右半边的当前元素小于左半边的当前元素（取右半边元素）
     * 4.右半边当前元素大于或者等于左半边当前元素（取左半边元素）
     * @param array 排序数组
     */
    public static void sort(Comparable[] array) {
        // 分配空间给辅助数组
        aux = new Comparable[array.length];
        sort(array, 0, array.length - 1);
    }

    /**
     * 将数组分成左半边和右半边两个数组（递归的一直到只剩一个元素）排序再归并回一个数组
     * @param array 排序的数组
     * @param lo 第一个元素下标
     * @param hi 最后一个元素下标
     */
    private static void sort(Comparable[] array, int lo, int hi) {
        if (hi <= lo) return;
        // 确认中间元素的下标
        int mid = lo + (hi - lo) / 2;
        // 递归的将左右分开排序（一直到只剩一个元素）
        sort(array, lo, mid);
        sort(array, (mid + 1), hi);
        merge(array, lo, mid, hi);
    }

    /**
     * 原地归并的抽象方法：
     * 先将 array 拷贝到辅助空间 aux 中，然后进行归并，归并的 4 个判断条件：
     * 1.左半边的元素用尽了（取右半边的元素）
     * 2.右半边的元素用尽了（取左半边的元素）
     * 3.右半边的当前元素小于左半边的当前元素（取右半边元素）
     * 4.右半边当前元素大于或者等于左半边当前元素（取左半边元素）
     * @param array 归并的数组
     * @param lo 第一个元素下标
     * @param mid 中间元素下标
     * @param hi 最后一个元素下标
     */
    private static void merge(Comparable[] array, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        // 将数组拷贝到辅助空间
        if (hi + 1 - lo >= 0) System.arraycopy(array, lo, aux, lo, hi + 1 - lo);
        // 将辅助空间的元素归并回数组中
        for (int k = lo; k <= hi; k++) {
            if (i > mid) array[k] = aux[j++];
            else if (j > hi) array[k] = aux[i++];
            else if (less(aux[j], aux[i])) array[k] = aux[j++];
            else array[k] = aux[i++];
        }
    }

    /**
     * 验证 v < w 是否为真
     * compareTo(): 如果被调用的对象比参数小则返回 -1，大于则返回 1， 等于返回 0
     *
     * @param v 比较元素1
     * @param w 比较元素2
     * @return 当 v < w 返回 true，其他情况都返回 false
     */
    private static boolean less(Comparable v, Comparable w) { return v.compareTo(w) < 0; }

    /**
     * 把 array 中下标为 i 和 j 的值进行交换
     *
     * @param array 两个元素所在的数组
     * @param i     交换元素下标
     * @param j     交换元素下标
     */
    private static void exch(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 打印 array 数组 [xxx,xxx]
     *
     * @param array 打印的数组
     */
    public static void show(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == 0) System.out.print("[" + array[i] + ", ");
            else if (i == array.length - 1) System.out.print(array[i] + "]");
            else System.out.print(array[i] + ", ");
        }
        System.out.println();
    }

    /**
     * 验证 array 是否已经排序
     *
     * @param array 验证的数组
     * @return true / false
     */
    public static boolean isSorted(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] array = {23, 34, 13, 23, 23, 5, 23, 4, 1, 45, 98, 10, 72, 14, 35};
        System.out.println(MergeSort.isSorted(array));
        MergeSort.sort(array);
        System.out.println(MergeSort.isSorted(array));
        MergeSort.show(array);
    }

}