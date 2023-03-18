package top.hanjie.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.context.ApplicationContext;
import top.hanjie.dto.Phone;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DefaultPhoneService implements PhoneService {

    private final static Map<String, PhoneService> PHONE_SERVICE_IMPLS = new ConcurrentHashMap<>();
    private static ApplicationContext APPLICATION_CONTEXT = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        // ioc 注入所有实现
        APPLICATION_CONTEXT = applicationContext;
    }
    public static PhoneService getService(String brand) {
        if (CollUtil.isEmpty(PHONE_SERVICE_IMPLS)) {
            synchronized (APPLICATION_CONTEXT) {
                PHONE_SERVICE_IMPLS.putAll(APPLICATION_CONTEXT.getBeansOfType(PhoneService.class));
            }
        }
        // 获取实现类
        return PHONE_SERVICE_IMPLS.getOrDefault(brand + "ServiceImpl", null);
    }

    @Override
    public void sayInfo(Phone phone) {
        System.out.println(this.getInfo(phone));
    }
    protected abstract String getInfo(Phone phone);
}
