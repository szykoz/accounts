package com.skoziol.accounts;

import com.skoziol.accounts.dto.AccountsContactInfoDto;
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
@EnableConfigurationProperties(AccountsContactInfoDto.class)
@OpenAPIDefinition(
	info = @Info(
			title = "Accounts microservice REST API Documentation",
			description = "MyBank Accounts microservice REST API Documentation",
			version = "V1",
			contact = @Contact(
					name = "Szymon Koziol",
					email = "skoziol.priv@gmail.com",
					url = "www.linkedin.com/in/szymon-kozioł-09713221b"
			),
			license = @License(
					name = "Apache 2.0",
					url = "https://www.apache.org/licenses/LICENSE-2.0"
			)
	),
		externalDocs = @ExternalDocumentation(
				description = "MyBank Accounts microservice REST API Documentation",
				url = "https://www.google.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
