package cine.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cineApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestión de Cine")
                        .description("API REST para gestión de cine, funciones y reservas")
                        .version("1.0"));
    }
}