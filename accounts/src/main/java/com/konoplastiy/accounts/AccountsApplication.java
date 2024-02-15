package com.konoplastiy.accounts;

import com.konoplastiy.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "KanapBank Accounts microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Andrew Kanap",
                        email = "konoplastiy@gmail.com",
                        url = "https://www.kanap.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.kanap.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "KanapBank Accounts microservice REST API Documentation",
                url = "https://www.kanap.com/swagger-ui.html"
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
