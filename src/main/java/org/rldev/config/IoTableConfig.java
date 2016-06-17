package org.rldev.config;

import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DigitalInput;
import org.rldev.iotable.model.ioUnits.DigitalOutput;
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
    public IoUnitValidator<DigitalInput> diSimpleValidator() {
        return new DiSimpleValidator();
    }

    @Bean
    public IoUnitValidator<AnalogOutput> aoSimpleValidator() {
        return new AoSimpleValidator();
    }

    @Bean
    public IoUnitValidator<DigitalOutput> doSimpleValidator() {
        return new DoSimpleValidator();
    }

    @Bean
    public IoUnitEqualityChecker ioUnitEqualator() {
        return new SimpleIoUnitEqualityChecker();
    }
}
