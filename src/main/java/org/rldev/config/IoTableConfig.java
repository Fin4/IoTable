package org.rldev.config;

import org.rldev.iotable.codegenerators.CodeGenerator;
import org.rldev.iotable.codegenerators.SimpleCodeGenerator;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DiscreteInput;
import org.rldev.iotable.model.ioUnits.DiscreteOutput;
import org.rldev.iotable.normalize.equality.IoUnitEqualityChecker;
import org.rldev.iotable.normalize.equality.SimpleIoUnitEqualityChecker;
import org.rldev.iotable.normalize.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoTableConfig {

    @Bean
    public IoUnitValidator<AnalogInput> aiSimpleValidator() {
        return new AiSimpleValidator();
    }

    @Bean
    public IoUnitValidator<DiscreteInput> diSimpleValidator() {
        return new DiSimpleValidator();
    }

    @Bean
    public IoUnitValidator<AnalogOutput> aoSimpleValidator() {
        return new AoSimpleValidator();
    }

    @Bean
    public IoUnitValidator<DiscreteOutput> doSimpleValidator() {
        return new DoSimpleValidator();
    }

    @Bean
    public IoUnitEqualityChecker ioUnitEqualityChecker() {
        return new SimpleIoUnitEqualityChecker();
    }

    @Bean
    public CodeGenerator simpleCodeGenerator() {
        return new SimpleCodeGenerator();
    }
}
