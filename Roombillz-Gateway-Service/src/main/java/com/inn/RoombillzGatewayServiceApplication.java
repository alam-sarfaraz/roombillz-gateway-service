package com.inn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "RoomBillz API Gateway Service",
        version = "v1",
        description = "RoomBillz API Gateway acts as the single entry point for all client requests. "
                + "It routes requests to the appropriate microservices, manages authentication, "
                + "handles security policies, and provides centralized API access for the RoomBillz platform. "
                + "The RoomBillz platform enables users to manage shared rooms, track expenses, "
                + "upload documents, schedule tasks, and split bills seamlessly within groups.",
        contact = @Contact(
            name = "Sarfaraz Alam",
            email = "sarfarazalam2702@gmail.com",
            url = "https://www.linkedin.com/in/alam-sarfaraz/"
        ),
        license = @License(
            name = "Apache License 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080/api/v1/gateway",
            description = "Local Development Server"
        ),
        @Server(
            url = "https://qa.api.roombillz.com/api/v1/gateway",
            description = "QA Environment"
        ),
        @Server(
            url = "https://api.roombillz.com/api/v1/gateway",
            description = "Production Environment"
        )
    },
    externalDocs = @ExternalDocumentation(
        description = "Official RoomBillz API Gateway Documentation",
        url = "https://roombillz.com/docs"
    )
)
public class RoombillzGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoombillzGatewayServiceApplication.class, args);
		System.out.println("************************************************** RoomBillz Gateway Service Started Successfully ************************************************************");
	}

}
