package com.material.management.config;

import com.material.management.domain.MaterialService;
import com.material.management.database.MongoDbMaterialRepository;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class DomainConfig {
    @Bean
    public MaterialService materialService(MongoDbMaterialRepository materialRepository){
        return new MaterialService(materialRepository);
    }
}
