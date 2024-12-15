package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.example.enums.VehicleType;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "vehicle")
public class Vehicle extends BaseEntity {

    @NotBlank(message = "Plate cannot be empty.")
    @Column(name = "plate", nullable = false)
    private String plate;

    @NotBlank(message = "Vehicle type cannot be empty.")
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToMany(mappedBy = "vehicle")
    private Set<Route> routes;

    //Constructors
    public Vehicle() {
    }

    public Vehicle(long id, LocalDate createdAt, String plate, VehicleType vehicleType, Company company) {
        super(id, createdAt);
        this.plate = plate;
        this.vehicleType = vehicleType;
        this.company = company;
    }

    //Getters
    public String getPlate() { return plate; }
    public VehicleType getVehicleType() { return vehicleType; }
    public Company getCompany() { return company; }
    public Set<Route> getRoutes() { return routes; }

    //Setters
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    //Methods
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleType=" + vehicleType +
                "plate=" + plate +
                '}';
    }
}
