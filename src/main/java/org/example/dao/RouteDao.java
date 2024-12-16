package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Route.CreateRouteDto;
import org.example.dto.Route.UpdateRouteDto;
import org.example.entity.Route;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.List;
public class RouteDao {
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
}