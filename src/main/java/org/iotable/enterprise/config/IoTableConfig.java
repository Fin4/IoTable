package org.iotable.enterprise.config;

import org.iotable.core.codegenerators.CodeGenerator;
import org.iotable.core.codegenerators.SimpleCodeGenerator;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.normalize.equality.IoUnitEqualityChecker;
import org.iotable.core.normalize.equality.SimpleIoUnitEqualityChecker;
import org.iotable.core.normalize.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoTableConfig {

    @Bean(name = "aiSimpleValidator")
    public IoUnitValidator<AnalogInput> aiSimpleValidator() {
        return new AiSimpleValidator();
    }

    @Bean(name = "diSimpleValidator")
    public IoUnitValidator<DiscreteInput> diSimpleValidator() {
        return new DiSimpleValidator();
    }

    @Bean(name = "aoSimpleValidator")
    public IoUnitValidator<AnalogOutput> aoSimpleValidator() {
        return new AoSimpleValidator();
    }

    @Bean(name = "doSimpleValidator")
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
