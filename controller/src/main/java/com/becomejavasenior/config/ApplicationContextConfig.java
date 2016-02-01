package com.becomejavasenior.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


@Configuration
@Import(value = {ConfigDao.class, ConfigService.class})
@ImportResource("classpath:/Context.xml")
//@ComponentScan(basePackages = "com.becomejavasenior")
public class ApplicationContextConfig {

}
