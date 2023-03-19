package top.hanjie.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjie.dto.Phone;
import top.hanjie.service.DefaultPhoneService;
import javax.validation.Valid;

@RestController
@RequestMapping("phone")
public class PhoneController {
    @PostMapping("sayInfo")
    public void sayInfo(@RequestBody @Valid Phone phone) {
        DefaultPhoneService.getService(phone.getBrand()).sayInfo(phone);
    }
}
