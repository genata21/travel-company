package org.example.dto.Company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class UpdateCompanyDto {

    @Positive(message = "Id must be positive number")
    private long id;

    @NotBlank(message = "Company must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Company name must start with a capital letter.")
    private String name;

    @NotBlank(message = "Company must have a description.")
    private String description;

    @PositiveOrZero(message = "Profit can't be negative")
    private BigDecimal profit;

    public UpdateCompanyDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateCompanyDto(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UpdateCompanyDto(long id, BigDecimal profit) {
        this.id = id;
        this.profit = profit;
    }

    public UpdateCompanyDto(long id, String name , String description, BigDecimal profit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.profit = profit;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getProfit() { return profit; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) { this.description = description; }
    public void setProfit(BigDecimal profit) { this.profit = profit; }

    @Override
    public String toString() {
        return "UpdateCompanyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", profit=" + profit +
                '}';
    }
}
