package top.hanjie.service;

import org.springframework.context.ApplicationContextAware;
import top.hanjie.dto.Phone;

public interface PhoneService extends ApplicationContextAware {
    void sayInfo(Phone phone);
}
