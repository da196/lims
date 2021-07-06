/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tz.go.tcra.lims.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author emmanuel.mfikwa
 */
@Configuration
@EnableAsync
public class LimsConfig {
    
    public static final String zone="Africa/Dar_es_Salaam";
    public static final String sender="no-reply@tcra.go.tz";
    
    @Value("${license.service.core.pool.size}")
    private int corePoolSize;
    
    @Value("${license.service.max.pool.size}")
    private int maxPoolSize;
    
    @Value("${license.service.max.que.size}")
    private int maxQueSize;
    
    @Bean
    public Executor taskExecutor() {
      ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      executor.setCorePoolSize(corePoolSize);
      executor.setMaxPoolSize(maxPoolSize);
      executor.setQueueCapacity(maxQueSize);
      executor.setThreadNamePrefix("ICHIS-");
      executor.initialize();
      return executor;
    }
    
    @Bean
    public Docket api() { 
        
            ParameterBuilder paramBuilder = new ParameterBuilder();
            List<Parameter> params = new ArrayList<>();
            paramBuilder
                    .name("Authorization")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(false)
                    .build();
            
            params.add(paramBuilder.build());
            
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api")
                .useDefaultResponseMessages(false)
                .globalOperationParameters(params)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo())
                .produces(new HashSet<String>(Arrays.asList("application/json")))
                .consumes(new HashSet<String>(Arrays.asList("application/json")));                                           
    }
    
    private ApiInfo apiInfo() {
            return new ApiInfo(
                    "Licensing Platform REST API", 
                    "An API for all communications to all services in exposed by the platform.", 
                    "API REFERENCE",
                    "Terms of service",
                    new Contact("TCRA Developers", "www.tcra.go.tz", "developers@tcra.go.tz"),
                    "License of API", 
                    "API license URL", 
                    Collections.emptyList());
    }
}
