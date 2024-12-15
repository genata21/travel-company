package org.example.dto.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.entity.Company;
import org.example.enums.Category;


public class CreateEmployeeDto {

    @NotBlank(message = "Employee must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Employee name must start with a capital letter.")
    private String name;

    @NotBlank(message = "Employee must have a category.")
    private Category category;

    @NotBlank(message = "Employee must be assigned to company")
    private Company company;

    public CreateEmployeeDto(String name, Category category, Company company) {
        this.name = name;
        this.category = category;
        this.company = company;
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
        return "CreateEmployeeDto{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", company=" + company +
                '}';
    }
}
