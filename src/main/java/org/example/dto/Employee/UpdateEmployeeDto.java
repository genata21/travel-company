package org.example.dto.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.example.entity.Company;
import org.example.enums.Category;
import java.math.BigDecimal;

public class UpdateEmployeeDto {

    @Positive(message = "Id must be positive number")
    private long id;

    @NotBlank(message = "Employee must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Employee name must start with a capital letter.")
    private String name;

    @NotBlank(message = "Employee must have a category.")
    private Category category;

    @Positive(message = "Salary can't be zero or negative.")
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @NotBlank(message = "Employee must be assigned to company")
    private Company company;

    public UpdateEmployeeDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateEmployeeDto(long id, BigDecimal salary) {
        this.id = id;
        this.salary = salary;
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

    public UpdateEmployeeDto(long id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public UpdateEmployeeDto(long id, Category category, BigDecimal salary) {
        this.id = id;
        this.category = category;
        this.salary = salary;
    }

    public UpdateEmployeeDto(long id, String name, BigDecimal salary, Company company) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.company = company;
    }

    public UpdateEmployeeDto(long id, String name , Category category, Company company) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.company = company;
    }

    public UpdateEmployeeDto(long id, String name, Category category, BigDecimal salary, Company company) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.salary = salary;
        this.company = company;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public BigDecimal getSalary() { return salary; }
    public Company getCompany() { return company; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(Category category) { this.category = category; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setCompany(Company company) { this.company = company; }

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