package io.github.jaywong1024.service.impl;

import org.springframework.stereotype.Service;
import io.github.jaywong1024.dto.Phone;
import io.github.jaywong1024.dto.Xiaomi;
import io.github.jaywong1024.service.DefaultPhoneService;

@Service("xiaomiServiceImpl")
public class XiaomiServiceImpl extends DefaultPhoneService {
    @Override
    protected String getInfo(Phone phone) {
        return ((Xiaomi) phone).getMakeFriend1999();
    }
}
