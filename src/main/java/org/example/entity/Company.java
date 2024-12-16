package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "company")
public class Company extends BaseEntity {

    @NotBlank(message = "Company must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Company name must start with a capital letter.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Company description cannot be empty.")
    @Column(name = "description", nullable = false)
    private String description;

    @PositiveOrZero(message = "Profit must not be negative number")
    @Column(name = "profit", nullable = true)
    private BigDecimal profit;

    @OneToMany(mappedBy = "company")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "company")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "company")
    private Set<Route> routes;

    //Constructors
    public Company() {
    }

    public Company(long id, LocalDate createdAt, String name) {
        super(id, createdAt);
        this.name = name;
    }

    public Company(long id, LocalDate createdAt, String name, BigDecimal profit) {
        super(id, createdAt);
        this.name = name;
        this.profit = profit;
    }
    public Company(String name, String description, BigDecimal profit) {
        this.name = name;
        this.description = description;
        this.profit = profit;
    }
    public Company(long id, LocalDate createdAt, BigDecimal profit, String description) {
        super(id, createdAt);
        this.description = description;
        this.profit = profit;
    }

    public Company(long id, LocalDate createdAt, String name, String description, BigDecimal profit) {
        super(id, createdAt);
        this.name = name;
        this.description = description;
        this.profit = profit;
    }

    public Company(long id, LocalDate createdAt, String name, String description) {
        super(id, createdAt);
        this.name = name;
        this.description = description;
    }

    //Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getProfit() { return profit; }

    //Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    //Methods

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", profit=" + profit +
                '}';
    }
}
