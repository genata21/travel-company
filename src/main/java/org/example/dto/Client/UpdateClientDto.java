package org.example.dto.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class UpdateClientDto {

    @Positive(message = "Id must be positive number")
    private long id;

    @NotBlank(message = "Client must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Client name must start with a capital letter.")
    private String name;

    @PositiveOrZero(message = "Debt can't be negative")
    private BigDecimal debt;

    public UpdateClientDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateClientDto(long id, BigDecimal debt) {
        this.id = id;
        this.debt = debt;
    }

    public UpdateClientDto(long id, String name, BigDecimal debt) {
        this.id = id;
        this.name = name;
        this.debt = debt;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getDebt() { return debt; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDebt(BigDecimal debt) { this.debt = debt; }

    @Override
    public String toString() {
        return "UpdateClientDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", debt=" + debt +
                '}';
    }
}

