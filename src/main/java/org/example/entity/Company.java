package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "company")
public class Company extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

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
