package top.hanjie.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Spring 上下文工具类
 *
 * @author 黄汉杰
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * 获取应用上下文
     *
     * @return org.springframework.context.ApplicationContext
     * @author 黄汉杰
     * @date 2022/4/15 0015 13:57
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 根据类型获取 bean
     *
     * @param clazz 类型
     * @return T
     * @author 黄汉杰
     * @date 2022/4/15 0015 13:51
     */
    public static <T> T getBean(Class<? extends T> clazz) {
        return APPLICATION_CONTEXT.getBean(clazz);
    }

    /**
     * 根据类型获取所有实现类
     *
     * @param clazz 类型
     * @return java.util.Map<java.lang.String, T>
     * @author 黄汉杰
     * @date 2022/4/15 0015 14:27
     */
    public static <T> Map<String, T> getBeans(Class<T> clazz) {
        return APPLICATION_CONTEXT.getBeansOfType(clazz);
    }

}