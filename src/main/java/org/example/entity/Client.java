package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "client")
public class Client extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "debt", nullable = true)
    private BigDecimal debt;

    @OneToMany(mappedBy = "client")
    private Set<Route> routes;

    //Constructors
    public Client() {
    }

    public Client(long id, LocalDate createdAt, String name) {
        super(id, createdAt);
        this.name = name;
        this.debt = BigDecimal.ZERO;
    }

    //Getters
    public String getName() { return name; }
    public BigDecimal getDebt() { return debt; }
    public Set<Route> getRoutes() { return routes; }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    //Methods

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", debt=" + debt +
                '}';
    }
}
