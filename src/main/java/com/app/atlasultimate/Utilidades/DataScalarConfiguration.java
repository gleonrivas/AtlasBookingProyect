package com.app.atlasultimate.Utilidades;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class DataScalarConfiguration {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.Time)
                .scalar(ExtendedScalars.Date);

    }
}
