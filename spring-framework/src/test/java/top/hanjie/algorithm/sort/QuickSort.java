package top.hanjie.algorithm.sort;

/**
 * 快速排序
 *
 * @author 黄汉杰
 * <p>描述：快速排序<p>
 */
public class QuickSort {

    /**
     * 快速排序：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
     * @param array 排序数组
     */
    public static void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * 递归切分排序数组
     * @param array 切分的数组
     * @param lo 起始位置
     * @param hi 结束位置
     */
    private static void sort(Comparable[] array, int lo, int hi) {
        // 切分到只剩下一个元素的时候停止
        if (hi <= lo) return;
        // 将元素切分并且返回切分元素的下标
        int j = partition(array, lo, hi);
        // 递归的继续切分切分元素左边的元素
        sort(array, lo, (j -1));
        // 递归的继续切分切分元素右边的元素
        sort(array, (j + 1), hi);
    }

    /**
     * 设定一个将数组切分成左右两边的元素 v，左边的元素都小于 v，右边的元素都大于或者等于 v
     * @param array 切分的数组
     * @param lo 切分的起始位置
     * @param hi 切分的结束位置
     * @return j 返回切分元素最后在数组中的下标
     */
    private static int partition(Comparable[] array, int lo, int hi) {
        // 左右扫描指针
        int i = lo, j = hi + 1;
        // 切分元素
        Comparable v = array[lo];
        while (true) {
            // 从左到右扫描元素，如果找到大于或者等于 v 的元素，将它的下标记下
            while (less(array[++i], v)) if (i == hi) break;
            // 从右到左扫描元素，如果找到小于 v 的元素，将它的下标记下
            while (less(v, array[--j])) if (j == lo) break;
            // 如果 array[i] 已经在 array[j] 右边的，则无需交换位置
            // 等于号的原因：可能存在一个元素，即大于或者等于 v，也小于等于 v
            if (i >= j) break;
            // 将大于或者等于 v 的元素和小于 v 的元素交换位置
            exch(array, i , j);
        }
        // 将切分元素放在切分好的元素的中间
        exch(array, lo, j);
        return j;
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
        System.out.println(QuickSort.isSorted(array));
        QuickSort.sort(array);
        System.out.println(QuickSort.isSorted(array));
        QuickSort.show(array);
    }

}