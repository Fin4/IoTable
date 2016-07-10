package org.iotable.enterprise.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "org.iotable.enterprise.*",
        exclude = {
        JacksonAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        DataSourceAutoConfiguration.class
        })
@Import({WebConfig.class, IoTableConfig.class})
public class AppConfig extends SpringBootServletInitializer {

    public static final String IOTABLES_DIRECTORY = "uploadIoTables";

    public static void main(String[] args) {
        System.getProperties().put("server.port", Integer.parseInt(System.getenv("PORT")));
        SpringApplication.run(AppConfig.class, args);
    }

/*    @Bean
    CommandLineRunner init() {
        return (String[] args) -> {
            new File(IOTABLES_DIRECTORY).mkdir();
        };
    }*/

}