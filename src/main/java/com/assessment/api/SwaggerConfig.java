package com.assessment.api;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.directModelSubstitute(XMLGregorianCalendar.class, String.class)
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.build().forCodeGeneration(true);
	}
}
