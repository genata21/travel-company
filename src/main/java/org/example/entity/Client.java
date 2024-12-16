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

@Entity(name = "client")
public class Client extends BaseEntity {

    @NotBlank(message = "Client must have a name.")
    @Pattern(regexp = "^[A-Z].*", message = "Client name must start with a capital letter.")
    @Column(name = "name", nullable = false)
    private String name;

    @PositiveOrZero(message = "Debt must not be negative number")
    @Column(name = "debt", nullable = true)
    private BigDecimal debt;

    @OneToMany(mappedBy = "client")
    private Set<Route> routes;

    public Client() {
    }

    public Client(long id, LocalDate createdAt) {
        super(id, createdAt);
    }

    public Client(long id, LocalDate createdAt, String name) {
        super(id, createdAt);
        this.name = name;
        this.debt = BigDecimal.ZERO;
    }

    public Client(long id, LocalDate createdAt, String name, BigDecimal debt) {
        super(id, createdAt);
        this.name = name;
        this.debt = debt;
    }

    public String getName() { return name; }
    public BigDecimal getDebt() { return debt; }
    public Set<Route> getRoutes() { return routes; }

    public void setName(String name) {
        this.name = name;
    }
    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", debt=" + debt +
                '}';
    }
}
