package io.github.jaywong1024.utils;

import java.util.BitSet;

/**
 * 布隆过滤器
 *
 * @author 黄汉杰
 * <p>描述：可用于防止缓存穿透<p>
 * <p>创建时间：2023/3/16 22:54<p>
 */
public class BloomFilter {

    /**
     * 位数组的大小
     */
    private static final int DEFAULT_SIZE = 2 << 24;
    /**
     * 位数组：数组中的元素只能是 0 或者 1
     */
    private final BitSet BITS = new BitSet(DEFAULT_SIZE);
    /**
     * 通过这个数组可以创建 6 个不同的哈希函数
     */
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};
    /**
     * 存放包含 hash 函数的类的数组
     */
    private final SimpleHash[] FUNC = new SimpleHash[SEEDS.length];

    /**
     * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
     */
    public BloomFilter() {
        // 初始化多个不同的 Hash 函数
        for (int i = 0; i < SEEDS.length; i++) {
            FUNC[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object... values) {
        for (Object val : values) {
            for (SimpleHash f : FUNC) {
                BITS.set(f.hash(val), true);
            }
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : FUNC) {
            ret = ret && BITS.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类。用于 hash 操作
     */
    private static class SimpleHash {

        private final int cap;
        private final int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null)
                    ? 0
                    : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }

    }

    public static void main(String[] args) {
        BloomFilter strBloomFilter = new BloomFilter();
        Object[] iKun = new String[]{"鸡", "你", "太", "美"};
        for (Object k : iKun) {
            System.out.print(strBloomFilter.contains(k) + ", ");
        }
        strBloomFilter.add(iKun);
        for (Object k : iKun) {
            System.out.print(strBloomFilter.contains(k) + ", ");
        }
        System.out.print(strBloomFilter.contains("荔枝") + ", ");
        System.out.println(strBloomFilter.contains("油饼"));
    }

}
