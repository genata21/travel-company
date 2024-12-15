package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.enums.Category;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "employee")
public class Employee extends BaseEntity {

    @NotBlank(message = "Employee must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Employee name must start with a capital letter.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Category cannot be empty.")
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "employee")
    private Set<Route> routes;

    //Constructors
    public Employee() {
    }

    public Employee(long id, LocalDate createdAt, String name, Category category, Company company) {
        super(id, createdAt);
        this.name = name;
        this.category = category;
        this.company = company;
    }

    //Getters
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public Company getCompany() { return company; }
    public Set<Route> getRoutes() { return routes; }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    //Methods
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", company=" + company +
                '}';
    }
}
