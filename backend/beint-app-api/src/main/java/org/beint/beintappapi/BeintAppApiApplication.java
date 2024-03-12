package org.beint.beintappapi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
@RequiredArgsConstructor
public class BeintAppApiApplication implements ApplicationListener<ApplicationReadyEvent> {

    private final Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(BeintAppApiApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        printConfigurationProperties();
    }

    private void printConfigurationProperties() {
        System.out.println("=== Application Configuration ===");
        String activeProfiles = String.join(",", environment.getActiveProfiles());
        System.out.println("Current Environment: " + activeProfiles);
        System.out.println("SERVER_PORT: " + environment.getProperty("server.port"));
        System.out.println("SPRING_DATASOURCE_URL: " + environment.getProperty("spring.datasource.url"));
        System.out.println("SPRING_DATASOURCE_USERNAME: " + environment.getProperty("spring.datasource.username"));
        System.out.println("SPRING_DATASOURCE_PASSWORD: " + environment.getProperty("spring.datasource.password"));
        
        System.out.println("===============================");
    }
}
