package tech.between.retotecnicomaxleon.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("tech.between.retotecnicomaxleon.infrastructure.adapter.rest.controller"))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.appName)
                .description("Documentacion para todos los servicios REST -  Between\n Utilizar las credenciales:\n" +
                        "{\n" +
                        "  \"password\": \"123456\",\n" +
                        "  \"username\": \"max\"\n" +
                        "}")
                .build();
    }
    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }
}
