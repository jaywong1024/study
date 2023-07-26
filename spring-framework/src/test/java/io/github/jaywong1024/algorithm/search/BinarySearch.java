package io.github.jaywong1024.algorithm.search;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 二分查找
 *
 * @author 黄汉杰
 * <p>描述：二分查找<p>
 */
public class BinarySearch {

    /**
     * 二分查找也称折半查找（Binary Search），它是一种效率较高的查找方法。
     * 但是，折半查找要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列
     * @param key 查询的值
     * @param array 被查询的数组
     * @return 返回key在数组中的下标，如果没找到返回 -1
     */
    public static int rank(int key, int[] array) {

        int lo = 0;
        int hi = array.length - 1;
        int mid;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (key > array[mid]) lo = mid + 1;
            else if (key < array[mid]) hi = mid - 1;
            else return mid;
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] array = {3, 5, 6, 1, 11, 34, 54, 32, 12, 65};
        Arrays.sort(array);
        System.out.print("数组排序后：[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) System.out.print(array[i]);
            else System.out.print(array[i] + ", ");
        }
        System.out.print("]，数组长度：" + array.length);
        System.out.println();
        System.out.print("请输入要查询的值：");
        Scanner scanner = new Scanner(System.in);
        int key = scanner.nextInt();
        scanner.close();
        System.out.println(key + "的下标为：" + BinarySearch.rank(key, array));
    }

}