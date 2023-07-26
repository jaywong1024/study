package io.github.jaywong1024.web;

import io.github.jaywong1024.Jay;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jay")
@RequiredArgsConstructor
public class JayWeb {
    private final Jay jay;
    @GetMapping("randomPlay")
    public String randomPlay() {
        return this.jay.randomPlay();
    }
}
