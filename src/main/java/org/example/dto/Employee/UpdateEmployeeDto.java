package org.example.dto.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.example.entity.Company;
import org.example.enums.Category;

public class UpdateEmployeeDto {

    @Positive(message = "Id must be positive number")
    private long id;

    @NotBlank(message = "Employee must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Employee name must start with a capital letter.")
    private String name;

    @NotBlank(message = "Employee must have a category.")
    private Category category;

    @NotBlank(message = "Employee must be assigned to company")
    private Company company;

    public UpdateEmployeeDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateEmployeeDto(long id, Category category) {
        this.id = id;
        this.category = category;
    }

    public UpdateEmployeeDto(long id, Company company) {
        this.id = id;
        this.company = company;
    }

    public UpdateEmployeeDto(long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public UpdateEmployeeDto(long id, String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public UpdateEmployeeDto(long id, Category category, Company company) {
        this.id = id;
        this.category = category;
        this.company = company;
    }

    public UpdateEmployeeDto(long id, String name ,Category category, Company company) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UpdateCompanyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
