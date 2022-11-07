package top.hanjie.rocksdb;

/**
 * 键值仓库
 *
 * @author 黄汉杰
 */
public interface KeyValueRepository<K, V> {

    /**
     * 保存键值对
     *
     * @param key   键
     * @param value 值
     * @author 黄汉杰
     * @date 2022/11/7 0007 16:28
     */
    void save(K key, V value);

    /**
     * 保存键值对（出现相同的键时追加值）
     *
     * @param key   键
     * @param value 值
     * @author 黄汉杰
     * @date 2022/11/7 0007 16:28
     */
    void merge(K key, V value);

    /**
     * 获取数据
     *
     * @param key 键
     * @return V 值
     * @author 黄汉杰
     * @date 2022/11/7 0007 16:29
     */
    V find(K key);

    /**
     * 删除键值对
     *
     * @param key 键
     * @author 黄汉杰
     * @date 2022/11/7 0007 16:29
     */
    void delete(K key);

}