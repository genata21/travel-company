package org.example.dto.Route;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
public class UpdateRouteDto {
    @Positive(message = "Id must be positive number")
    private long id;
    @Future(message = "Delivery date must be in the future.")
    private LocalDate deliveryDate;
    @NotNull(message = "Paid cannot be empty.")
    private boolean isPaid;
    @PositiveOrZero(message = "Cost must not be negative number")
    private BigDecimal cost;
    public UpdateRouteDto(long id, LocalDate deliveryDate) {
        this.id = id;
        this.deliveryDate = deliveryDate;
    }
    public UpdateRouteDto(long id, boolean isPaid) {
        this.id = id;
        this.isPaid = isPaid;
    }
    public UpdateRouteDto(long id, BigDecimal cost) {
        this.id = id;
        this.cost = cost;
    }
    public UpdateRouteDto(long id, BigDecimal cost, boolean isPaid) {
        this.id = id;
        this.cost = cost;
        this.isPaid = isPaid;
    }
    public UpdateRouteDto(long id, LocalDate deliveryDate, boolean isPaid, BigDecimal cost) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.isPaid = isPaid;
        this.cost = cost;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
    public BigDecimal getCost() { return cost; }
    public void setCost(BigDecimal cost) { this.cost = cost; }
    @Override
    public String toString() {
        return "UpdateRouteDto{" +
                "id=" + id +
                ", deliveryDate=" + deliveryDate +
                ", isPaid=" + isPaid +
                ", cost=" + cost +
                '}';
    }
}