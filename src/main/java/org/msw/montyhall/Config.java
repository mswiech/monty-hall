package org.msw.montyhall;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Martin Swiech on 6.10.2016.
 */
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "org.msw.montyhall")
@PropertySource("classpath:config.properties")
public class Config {

    @Bean()
    public PropertyPlaceholderAutoConfiguration propertyPlaceholderAutoConfiguration() {
        return new PropertyPlaceholderAutoConfiguration();
    }
}
