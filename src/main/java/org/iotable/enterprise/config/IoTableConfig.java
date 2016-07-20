package org.iotable.enterprise.config;

import org.iotable.core.codegenerators.*;
import org.iotable.core.codegenerators.simple.SimpleAiCodeGenerator;
import org.iotable.core.codegenerators.simple.SimpleAoCodeGenerator;
import org.iotable.core.codegenerators.simple.SimpleDiCodeGenerator;
import org.iotable.core.codegenerators.simple.SimpleDoCodeGenerator;
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
    public AiCodeGenerator simpleAiCodeGenerator() {
        return new SimpleAiCodeGenerator();
    }

    @Bean
    public DiCodeGenerator simpleDiCodeGenerator() {
        return new SimpleDiCodeGenerator();
    }

    @Bean
    public AoCodeGenerator simpleAoCodeGenerator() {
        return new SimpleAoCodeGenerator();
    }

    @Bean
    public DoCodeGenerator simpleDoCodeGenerator() {
        return new SimpleDoCodeGenerator();
    }
}
