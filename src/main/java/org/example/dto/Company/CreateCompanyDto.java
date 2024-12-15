package org.example.dto.Company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class CreateCompanyDto {

    @NotBlank(message = "Company must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Company name must start with a capital letter.")
    private String name;

    @NotBlank(message = "Company must have a description.")
    private String description;

    @PositiveOrZero(message = "Profit must not be negative number")
    private BigDecimal profit;

    public CreateCompanyDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CreateCompanyDto(String name, String description, BigDecimal profit) {
        this.name = name;
        this.description = description;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "CreateCompanyDto{" +
                "name=" + name +
                ", description='" + description + '\'' +
                ", profit=" + profit +
                '}';
    }
}
