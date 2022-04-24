package top.hanjie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hanjie.entity.Test;

import java.util.List;

/**
 * 测试
 *
 * @author 黄汉杰
 */
public interface TestMapper extends BaseMapper<Test> {

    /**
     * 测试 union all
     * @author 黄汉杰
     * @date 2022/4/24 0024 18:42
     * @return java.util.List<top.hanjie.entity.Test>
     */
    List<Test> unionAll();

    /**
     * 测试 join
     * @author 黄汉杰
     * @date 2022/4/24 0024 18:42
     * @return java.util.List<top.hanjie.entity.Test>
     */
    List<Test> join();

}