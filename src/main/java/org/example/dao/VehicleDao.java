package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Vehicle.CreateVehicleDto;
import org.example.dto.Vehicle.UpdateVehicleDto;
import org.example.entity.Route;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class VehicleDao {

    public static void createVehicle(CreateVehicleDto vehicleDto) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Vehicle vehicle = new Vehicle();
            vehicle.setPlate(vehicleDto.getPlate());
            vehicle.setVehicleType(vehicleDto.getType());
            vehicle.setCompany(vehicleDto.getCompany());
            vehicle.setCreatedAt(LocalDate.now());

            session.save(vehicle);
            transaction.commit();
        }
    }

    public static Vehicle getVehicleById(long vehicleId) {
        Vehicle vehicle;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.createQuery(
                    "SELECT v FROM vehicle v " +
                            "join fetch v.company " +
                            "WHERE v.id = :vehicleId " +
                            "AND v.deletedAt IS NULL", Vehicle.class)
                    .setParameter("vehicleId", vehicleId)
                    .getSingleResult();
            transaction.commit();
        }

        return vehicle;
    }

    public static List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery(
                            "SELECT v FROM vehicle v " +
                                    "join fetch v.company " +
                                    "WHERE v.deletedAt IS NULL", Vehicle.class)
                    .getResultList();
            transaction.commit();
        }

        return vehicles;
    }

    public static List<Vehicle> getCompanyVehicles(long companyId) {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery(
                            "SELECT v FROM vehicle v " +
                                    "join fetch v.company " +
                                    "WHERE v.company.id = :companyId " +
                                    "AND v.deletedAt IS NULL",
                            Vehicle.class)
                    .setParameter("companyId", companyId)
                    .getResultList();
            transaction.commit();
        }

        return vehicles;
    }

    public static List<Route> getAllRoutesDoneByVehicle(long vehicleId) {
        List<Route> routes;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            routes = session
                    .createQuery(
                            "SELECT r FROM route r " +
                                    "join fetch r.vehicle " +
                                    "join fetch r.company " +
                                    "join fetch r.employee " +
                                    "join fetch r.client " +
                                    "WHERE r.vehicle.id = :vehicleId " +
                                    "AND r.deletedAt IS NULL",
                            Route.class)
                    .setParameter("vehicleId", vehicleId)
                    .getResultList();
            transaction.commit();
        }

        return routes;
    }

    public static void updateVehiclePlate(UpdateVehicleDto updateVehicleDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE vehicle v SET v.plate = :plate " +
                                    "WHERE v.id = :id " +
                                    "AND v.deletedAt IS NULL")
                    .setParameter("plate", updateVehicleDto.getPlate())
                    .setParameter("id", updateVehicleDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void softDeleteVehicle(long vehicleId) {
        LocalDate deleteDate = LocalDate.now();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE vehicle v SET v.deletedAt = :deleteDate " +
                                    "WHERE v.id = :id")
                    .setParameter("deleteDate", deleteDate)
                    .setParameter("id", vehicleId)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void hardDeleteVehicle(Vehicle vehicle) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }
}
