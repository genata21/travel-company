package org.example.dto.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateClientDto {

    @NotBlank(message = "Client must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Client name must start with a capital letter.")
    private String name;

    public CreateClientDto(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "CreateClientDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
