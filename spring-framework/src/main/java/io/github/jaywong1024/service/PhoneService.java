package io.github.jaywong1024.service;

import org.springframework.context.ApplicationContextAware;
import io.github.jaywong1024.dto.Phone;

public interface PhoneService extends ApplicationContextAware {
    void sayInfo(Phone phone);
}
