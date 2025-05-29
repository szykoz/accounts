package com.skoziol.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public record CustomerDto(
        @Schema(
                description = "Name of the customer", example = "Jan Kowalski"
        )
        @NotEmpty(message = "Name must not be null or empty")
        @Size(min = 5, max = 30, message = "The lenght of the customer name should be between 5 and 30")
        String name,

        @Schema(
                description = "Email address of the customer", example = "jkowalski@google.com"
        )
        @NotEmpty(message = "Email address must not be null or empty")
        @Email(message = "Email address should be a valid value")
        String email,

        @Schema(
                description = "Mobile number of the customer", example = "9345432123"
        )
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        String mobileNumber,

        AccountsDto accountsDto
) {
}
