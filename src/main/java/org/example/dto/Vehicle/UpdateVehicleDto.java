package org.example.dto.Vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class UpdateVehicleDto {

    @Positive(message = "Id must be positive number")
    private long id;

    @NotBlank(message = "Vehicle must have a plate number")
    private String plate;

    public UpdateVehicleDto(long id, String plate) {
        this.id = id;
        this.plate = plate;
    }
    public long getId() { return id; }
    public String getPlate() { return plate; }

    public void setId(long id) { this.id = id; }
    public void setPlate(String plate) { this.plate = plate; }

    @Override
    public String toString() {
        return "UpdateVehicleDto{" +
                "id=" + id +
                ", plate='" + plate + '\'' +
                '}';
    }
}

