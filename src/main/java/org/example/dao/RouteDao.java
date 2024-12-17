package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Route.CreateRouteDto;
import org.example.dto.Route.UpdateRouteDto;
import org.example.dto.Vehicle.CreateVehicleDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import java.io.*;
import org.example.enums.CargoType;

public class RouteDao {

    public static final String FILE_NAME = "transports.csv";

    public static void CreateRoute(CreateRouteDto createRouteDto) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Route route = new Route();
            route.setCreatedAt(LocalDate.now());
            route.setStartingPoint(createRouteDto.getStartingPoint());
            route.setDestination(createRouteDto.getDestination());
            route.setStartDate(createRouteDto.getStartDate());
            route.setDeliveryDate(createRouteDto.getDeliveryDate());
            route.setCargoType(createRouteDto.getCargoType());
            route.setCost(createRouteDto.getCost());
            route.setPaid(createRouteDto.isPaid());
            if(createRouteDto.getWeight() != null) {
                route.setWeight(createRouteDto.getWeight());
            }
            route.setCompany(createRouteDto.getCompany());
            route.setEmployee(createRouteDto.getEmployee());
            route.setVehicle(createRouteDto.getVehicle());
            route.setClient(createRouteDto.getClient());
            session.save(route);
            transaction.commit();
        }
    }
    public static Route getRouteById(long routeId) {
        Route route;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            route = session.createQuery(
                            "SELECT r FROM route r " +
                                    "join fetch r.company " +
                                    "join fetch r.employee " +
                                    "join fetch r.vehicle " +
                                    "join fetch r.client " +
                                    "WHERE r.id = :routeId " +
                                    "AND r.deletedAt IS NULL", Route.class)
                    .setParameter("routeId", routeId)
                    .getSingleResult();
            transaction.commit();
        }
        return route;
    }
    public static List<Route> getAllRoutes() {
        List<Route> routes;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            routes = session.createQuery(
                            "SELECT r FROM route r " +
                                    "join fetch r.company " +
                                    "join fetch r.employee " +
                                    "join fetch r.vehicle " +
                                    "join fetch r.client " +
                                    "WHERE r.deletedAt IS NULL", Route.class)
                    .getResultList();
            transaction.commit();
        }
        return routes;
    }

    public static boolean routeIsPaid(long routeId) {
        return getRouteById(routeId).isPaid();
    }

    public static void payRoute(long routeId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE route r " +
                                    "SET r.isPaid = TRUE " +
                                    "WHERE r.id = :routeId " +
                                    "AND r.deletedAt IS NULL")
                    .setParameter("routeId", routeId)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateRoute(UpdateRouteDto updateRouteDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE route r " +
                                    "SET r.isPaid = :isPaid," +
                                    "r.deliveryDate = :deliveryDate, " +
                                    "r.cost = :cost " +
                                    "WHERE r.id = :routeId " +
                                    "AND r.deletedAt IS NULL")
                    .setParameter("routeId", updateRouteDto.getId())
                    .setParameter("isPaid", updateRouteDto.isPaid())
                    .setParameter("deliveryDate", updateRouteDto.getDeliveryDate())
                    .setParameter("cost", updateRouteDto.getCost())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void softDeleteRoute(long routeId) {
        LocalDate deleteDate = LocalDate.now();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE route r SET r.deletedAt = :deleteDate " +
                                    "WHERE r.id = :routeId")
                    .setParameter("deleteDate", deleteDate)
                    .setParameter("routeId", routeId)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void hardDeleteRoute(Route route) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(route);
            transaction.commit();
        }
    }

    public static List<Route> getRoutesByDestination(String destination) {
        List<Route> routes;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            routes = session.createQuery(
                            "SELECT r FROM route r " +
                                    "join fetch r.company " +
                                    "join fetch r.employee " +
                                    "join fetch r.vehicle " +
                                    "join fetch r.client " +
                                    "WHERE r.destination LIKE %:destination% " +
                                    "AND r.deletedAt IS NULL", Route.class)
                    .setParameter("destination", destination)
                    .getResultList();
            transaction.commit();
        }
        return routes;
    }

    public static void saveToFile(String filename) {
        List<Route> allRoutes = RouteDao.getAllRoutes();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Route routes : allRoutes) {
                writer.write(routes.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader("textNew.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Route route = new Route();

                route.setCreatedAt(LocalDate.parse(data[0]));
                route.setCargoType(CargoType.valueOf(data[1].toUpperCase());
                route.setCost(new BigDecimal(data[2]));
                route.setDeliveryDate(LocalDate.parse(data[3]));
                route.setDestination(data[4]);
                route.setPaid(Boolean.parseBoolean(data[5]));
                route.setStartDate(LocalDate.parse(data[6]));
                route.setStartingPoint(data[7]);
                route.setCost(new BigDecimal(data[8]));




                Client client = ClientDao.getClientById(Long.parseLong(data[9]));
                Company company = CompanyDao.getCompanyById(Long.parseLong(data[10]));
                Employee employee = EmployeeDao.getEmployeeById(Long.parseLong(data[11]));
                Vehicle vehicle = VehicleDao.getVehicleById(Long.parseLong(data[12]));

                route.setVehicle(vehicle);
                route.setCompany(company);
                route.setEmployee(employee);
                route.setClient(client);

                System.out.println(Arrays.toString(data));
//                // Save the transport to the database
//                transportDAO.save(transport);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}