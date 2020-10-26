package com.kexie.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.kexie.mapper")
@Configuration
public class MapperConfig {
}
