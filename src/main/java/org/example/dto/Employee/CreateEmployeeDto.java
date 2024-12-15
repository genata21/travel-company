package org.example.dto.Employee;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.example.entity.Company;
import org.example.enums.Category;
import java.math.BigDecimal;
public class CreateEmployeeDto {
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
    public CreateEmployeeDto(String name, Category category, BigDecimal salary, Company company) {
        this.name = name;
        this.category = category;
        this.salary = salary;
        this.company = company;
    }
    public String getName() { return name; }
    public Category getCategory() { return category; }
    public BigDecimal getSalary() { return salary; }
    public Company getCompany() { return company; }
    public void setName(String name) { this.name = name; }
    public void setCategory(Category category) { this.category = category; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public void setCompany(Company company) { this.company = company; }
    @Override
    public String toString() {
        return "CreateEmployeeDto{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", salary=" + salary +
                ", company=" + company +
                '}';
    }
}