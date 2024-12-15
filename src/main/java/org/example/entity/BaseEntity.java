package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

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
