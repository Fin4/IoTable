package org.iotable.enterprise.config;

import org.iotable.core.mappers.*;
import org.iotable.core.mappers.simple.SimpleAiMapper;
import org.iotable.core.mappers.simple.SimpleAoMapper;
import org.iotable.core.mappers.simple.SimpleDiMapper;
import org.iotable.core.mappers.simple.SimpleDoMapper;
import org.iotable.core.normalize.equality.IoTableEqualityChecker;
import org.iotable.core.normalize.equality.SimpleIoTableEqualityChecker;
import org.iotable.core.normalize.validation.*;
import org.iotable.core.normalize.validation.simple.SimpleAiValidator;
import org.iotable.core.normalize.validation.simple.SimpleAoValidator;
import org.iotable.core.normalize.validation.simple.SimpleDiValidator;
import org.iotable.core.normalize.validation.simple.SimpleDoValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoTableConfig {

    /** validators*/
    @Bean
    public AiValidator aiSimpleValidator() {
        return new SimpleAiValidator();
    }

    @Bean
    public DiValidator diSimpleValidator() {
        return new SimpleDiValidator();
    }

    @Bean
    public AoValidator aoSimpleValidator() {
        return new SimpleAoValidator();
    }

    @Bean
    public DoValidator doSimpleValidator() {
        return new SimpleDoValidator();
    }

    /** duplicates*/
    @Bean
    public IoTableEqualityChecker ioTableEqualityChecker() {
        return new SimpleIoTableEqualityChecker();
    }

    /** code generators*/
    @Bean
    public AiMapper simpleAiMapper() {
        return new SimpleAiMapper();
    }

    @Bean
    public DiMapper simpleDiMapper() {
        return new SimpleDiMapper();
    }

    @Bean
    public AoMapper simpleAoMapper() {
        return new SimpleAoMapper();
    }

    @Bean
    public DoMapper simpleDoMapper() {
        return new SimpleDoMapper();
    }
}
