package org.example.dto.Vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.entity.Company;
import org.example.enums.VehicleType;

public class CreateVehicleDto {

    @NotBlank(message = "Vehicle must have a plate number")
    private String plate;

    @NotNull(message = "Vehicle must have a type")
    private VehicleType type;

    @NotNull(message = "Vehicle must be assigned to company")
    private Company company;

    public CreateVehicleDto(String plate, VehicleType type, Company company) {
        this.plate = plate;
        this.type = type;
        this.company = company;
    }

    public String getPlate() { return plate; }
    public VehicleType getType() { return type; }
    public Company getCompany() { return company; }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    public void setType(VehicleType type) {
        this.type = type;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "CreateVehicleDto{" +
                "plate='" + plate + '\'' +
                ", type=" + type +
                ", company=" + company +
                '}';
    }
}

