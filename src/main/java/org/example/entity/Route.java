package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Starting Date cannot be empty.")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Delivery Date cannot be empty.")
    @FutureOrPresent(message = "Date cannot be in the past.")
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @NotNull(message = "Cargo type cannot be empty.")
    @Enumerated(EnumType.STRING)
    @Column(name = "cargo_type", nullable = false)
    private CargoType cargoType;

    @NotNull(message = "Paid cannot be empty.")
    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @NotNull(message = "Cost cannot be empty.")
    @PositiveOrZero(message = "Cost must not be negative number")
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @NotNull(message = "Weight cannot be empty.")
    @PositiveOrZero(message = "Weight must not be negative number")
    @Column(name = "weight", nullable = true)
    private BigDecimal weight;

    @NotNull(message = "Route must be have at least one vehicle")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @NotNull(message = "Route must have company")
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @NotNull(message = "Route must be have at least one employee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @NotNull(message = "Route must be have at least one client")
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

// without starting point
    public Route(long id, LocalDate createdAt, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        super(id, createdAt);
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

//without destination
    public Route(long id, LocalDate createdAt, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, String startingPoint, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        super(id, createdAt);
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.startingPoint = startingPoint;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

//without start date
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

    //without delivery date
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, CargoType cargoType, LocalDate startDate, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.cargoType = cargoType;
        this.startDate = startDate;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

//without cargo type
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

//without isPaid
    public Route(long id, LocalDate createdAt, CargoType cargoType, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Client client, Employee employee) {
        super(id, createdAt);
        this.cargoType = cargoType;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.client = client;
        this.employee = employee;
    }

//without cost
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

    //without cost
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, Vehicle vehicle, Company company, Employee employee, Client client, BigDecimal cost) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
        this.cost = cost;
    }

    public Route(String startingPoint, String destination, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee, Client client) {
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
        this.client = client;
    }

// without vehicle
public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Company company, Employee employee, Client client) {
    super(id, createdAt);
    this.startingPoint = startingPoint;
    this.destination = destination;
    this.startDate = startDate;
    this.deliveryDate = deliveryDate;
    this.cargoType = cargoType;
    this.isPaid = isPaid;
    this.cost = cost;
    this.weight = weight;
    this.company = company;
    this.employee = employee;
    this.client = client;
}

    // without company
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Employee employee, Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.employee = employee;
        this.client = client;
    }

    // without employee
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Client client) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.client = client;
    }

    // without client
    public Route(long id, LocalDate createdAt, String startingPoint, String destination, LocalDate startDate, LocalDate deliveryDate, CargoType cargoType, boolean isPaid, BigDecimal cost, BigDecimal weight, Vehicle vehicle, Company company, Employee employee) {
        super(id, createdAt);
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startDate = startDate;
        this.deliveryDate = deliveryDate;
        this.cargoType = cargoType;
        this.isPaid = isPaid;
        this.cost = cost;
        this.weight = weight;
        this.vehicle = vehicle;
        this.company = company;
        this.employee = employee;
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
