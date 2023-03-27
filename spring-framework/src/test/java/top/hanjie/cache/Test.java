package top.hanjie.cache;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * 缓存测试
 *
 * @author 黄汉杰
 * <p>描述：测试<p>
 * <p>创建时间：2023/3/16 23:26<p>
 */
public class Test {

    public static void main(String[] args) {
        // 创建布隆过滤器对象
        BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.defaultCharset()),
                2 << 24,
                0.0001);
        System.out.println(filter.mightContain("鸡你"));
        System.out.println(filter.mightContain("太美"));
        filter.put("鸡你");
        filter.put("太美");
        System.out.println(filter.mightContain("鸡你"));
        System.out.println(filter.mightContain("太美"));
    }

}