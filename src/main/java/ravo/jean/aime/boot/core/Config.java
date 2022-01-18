package ravo.jean.aime.boot.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ravo.jean.aime.boot.annotation.AnnotationServiceImpl;

@Configuration
public class Config {

    @Bean
    public AnnotationServiceImpl myAnnotation() {
        return new AnnotationServiceImpl();
    }
}
