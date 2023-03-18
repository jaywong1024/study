package top.hanjie.service.impl;

import org.springframework.stereotype.Service;
import top.hanjie.dto.Phone;
import top.hanjie.dto.Xiaomi;
import top.hanjie.service.DefaultPhoneService;

@Service("xiaomiServiceImpl")
public class XiaomiServiceImpl extends DefaultPhoneService {
    @Override
    protected String getInfo(Phone phone) {
        return ((Xiaomi) phone).getMakeFriend1999();
    }
}
