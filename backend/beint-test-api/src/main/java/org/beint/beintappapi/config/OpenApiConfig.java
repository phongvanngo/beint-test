package org.beint.beintappapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        License license = new License();
        license.setName("BEINT APP API ");
        Contact contact = new Contact();
        contact.setName("Beint");

        Info info = new Info();
        info.setVersion("v3");
        info.title("Beint  Test Api").description(
                        "Documentations for Beint Test API v1.0").license(license)
                .setContact(contact);
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
        return openAPI;
    }
}