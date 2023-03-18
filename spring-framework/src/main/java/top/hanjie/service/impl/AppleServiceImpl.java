package top.hanjie.service.impl;

import org.springframework.stereotype.Service;
import top.hanjie.dto.Apple;
import top.hanjie.dto.Phone;
import top.hanjie.service.DefaultPhoneService;

@Service("appleServiceImpl")
public class AppleServiceImpl extends DefaultPhoneService {
    @Override
    protected String getInfo(Phone phone) {
        return ((Apple) phone).getIsland();
    }
}
