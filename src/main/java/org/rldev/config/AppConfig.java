package org.rldev.config;


import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DigitalInput;
import org.rldev.iotable.model.ioUnits.DigitalOutput;
import org.rldev.iotable.normalize.validation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.io.File;

@SpringBootApplication(scanBasePackages = "org.rldev.*", exclude = { JacksonAutoConfiguration.class, SecurityAutoConfiguration.class})
@Import({WebConfig.class, IoTableConfig.class})
public class AppConfig extends SpringBootServletInitializer {

    public static final String IOTABLES_DIRECTORY = "uploadIoTables";

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(IOTABLES_DIRECTORY).mkdir();
        };
    }
}
