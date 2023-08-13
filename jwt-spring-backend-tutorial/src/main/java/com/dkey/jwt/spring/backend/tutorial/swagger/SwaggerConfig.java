package com.dkey.jwt.spring.backend.tutorial.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
	
	private ApiInfo apiInfo(){
        return new ApiInfo(
                "Tutorial JWT",
                "Description",
                "2.0",
                "Contact info",
                new Contact("Dkey Group", "dkey.com", "dkeyceo@gmail.com"),
                "DkeyLic",
                "license.dkey.com",
                Collections.emptyList()
        );
    }
}
