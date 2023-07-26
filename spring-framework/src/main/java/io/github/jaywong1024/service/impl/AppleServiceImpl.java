package io.github.jaywong1024.service.impl;

import org.springframework.stereotype.Service;
import io.github.jaywong1024.dto.Apple;
import io.github.jaywong1024.dto.Phone;
import io.github.jaywong1024.service.DefaultPhoneService;

@Service("appleServiceImpl")
public class AppleServiceImpl extends DefaultPhoneService {
    @Override
    protected String getInfo(Phone phone) {
        return ((Apple) phone).getIsland();
    }
}
