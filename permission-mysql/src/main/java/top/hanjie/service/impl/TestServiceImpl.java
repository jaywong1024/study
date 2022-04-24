package top.hanjie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.hanjie.entity.Test;
import top.hanjie.mapper.TestMapper;
import top.hanjie.service.TestService;

/**
 * 测试接口实现
 *
 * @author 黄汉杰
 */
@Service
public class TestServiceImpl
        extends ServiceImpl<TestMapper, Test>
        implements TestService {

}