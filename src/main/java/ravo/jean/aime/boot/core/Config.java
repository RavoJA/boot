package ravo.jean.aime.boot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ravo.jean.aime.boot.annotation.BootInternalAnnotationservice;

@Configuration
public class Config {

    @Bean
    public BootInternalAnnotationservice myAnnotation() {
        return new BootInternalAnnotationservice();
    }
}
