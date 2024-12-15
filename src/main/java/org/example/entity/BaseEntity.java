package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    @PositiveOrZero(message = "ID must not be negative number")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Date cannot be empty.")
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @NotBlank(message = "Date cannot be empty.")
    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    public BaseEntity() {
    }

    public BaseEntity(long id, LocalDate createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getDeletedAt() { return deletedAt; }

    public void setId(long id) { this.id = id; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public void setDeletedAt(LocalDate deletedAt) { this.deletedAt = deletedAt; }
}
