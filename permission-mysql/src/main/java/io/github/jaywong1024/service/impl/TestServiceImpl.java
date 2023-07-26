package io.github.jaywong1024.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import io.github.jaywong1024.entity.Test;
import io.github.jaywong1024.mapper.TestMapper;
import io.github.jaywong1024.service.TestService;

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