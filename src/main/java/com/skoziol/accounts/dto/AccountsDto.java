package com.skoziol.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public record AccountsDto(
        @NotEmpty(message = "Account number must not be null or empty")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
        Long accountNumber,

        @NotEmpty(message = "Account type must not be null or empty")
        String accountType,

        @NotEmpty(message = "Branch address must not be null or empty")
        String branchAddress
) {
}
