package io.github.jaywong1024;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JayAutoConfig {
    @Bean
    @ConditionalOnClass(Jay.class)
    public Jay jay() {
        return new Jay();
    }
}
