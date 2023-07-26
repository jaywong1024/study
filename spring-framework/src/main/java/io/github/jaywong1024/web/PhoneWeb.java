package io.github.jaywong1024.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.jaywong1024.dto.Phone;
import io.github.jaywong1024.service.DefaultPhoneService;
import javax.validation.Valid;

@RestController
@RequestMapping("phone")
public class PhoneWeb {
    @PostMapping("sayInfo")
    public void sayInfo(@RequestBody @Valid Phone phone) {
        DefaultPhoneService.getService(phone.getBrand()).sayInfo(phone);
    }
}
