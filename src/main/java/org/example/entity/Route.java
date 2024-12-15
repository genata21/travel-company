package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.enums.CargoType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "route")
public class Route extends BaseEntity {

    @NotBlank(message = "Starting point cannot be empty.")
    @Column(name = "starting_point", nullable = false)
    private String startingPoint;

    @NotBlank(message = "Destination cannot be empty.")
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotBlank(message = "Date cannot be empty.")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotBlank(message = "Date cannot be empty.")
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @NotBlank(message = "Cargo type cannot be empty.")
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type", nullable = false)
    private CargoType cargoType;

    @NotBlank(message = "Paid cannot be empty.")
    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @PositiveOrZero(message = "Cost must not be negative number")
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @PositiveOrZero(message = "Weight must not be negative number")
    @Column(name = "weight", nullable = true)
    private BigDecimal weight;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    //Constructors
    public Route() {
    }

    public Route(
            long id,
            LocalDate createdAt,
            String startingPoint,
            String destination,
            LocalDate startDate,
            LocalDate deliveryDate,
            CargoType cargoType,
            BigDecimal cost,
            Vehicle vehicle,
            Company company,
            Employee employee,
            Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.cost = cost;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
        this.isPaid = false;
    }

    public Route(
            long id,
            LocalDate createdAt,
            String startingPoint,
            String destination,
            LocalDate startDate,
            LocalDate deliveryDate,
            CargoType cargoType,
            BigDecimal cost,
            BigDecimal weight,
            Vehicle vehicle,
            Company company,
            Employee employee,
            Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

    //Getters
    public String getStartingPoint() { return startingPoint; }
    public String getDestination() { return destination; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public CargoType getCargoType() { return cargoType; }
    public boolean isPaid() { return isPaid; }
    public BigDecimal getCost() { return cost; }
    public BigDecimal getWeight() { return weight; }
    public Vehicle getVehicle() { return vehicle; }
    public Company getCompany() { return company; }
    public Employee getEmployee() { return employee; }
    public Client getClient() { return client; }

    //Setters

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    //Methods

    @Override
    public String toString() {
        return "Route{" +
                "startingPoint='" + startingPoint + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", deliveryDate=" + deliveryDate +
                ", cargoType=" + cargoType +
                ", isPaid=" + isPaid +
                ", cost=" + cost +
                ", weight=" + weight +
                ", vehicle=" + vehicle +
                ", company=" + company +
                ", employee=" + employee +
                ", client=" + client +
                '}';
    }
}
