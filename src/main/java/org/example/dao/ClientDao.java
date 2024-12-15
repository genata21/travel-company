package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Client.CreateClientDto;
import org.example.dto.Client.UpdateClientDto;
import org.example.entity.Client;
import org.example.entity.Route;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ClientDao {

    public static void createClient(CreateClientDto createClientDto) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Client client = new Client();
            client.setName(createClientDto.getName());
            client.setDebt(BigDecimal.ZERO);
            client.setCreatedAt(LocalDate.now());

            session.save(client);
            transaction.commit();
        }
    }

    //TESTED - error when there are no routes for this client
    public static Client getClientById(long clientId) {
        Client client;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.createQuery(
                            "SELECT c FROM client c " +
                                    "join fetch c.routes " +
                                    "WHERE c.id = :clientId " +
                                    "AND c.deletedAt IS NULL", Client.class)
                    .setParameter("clientId", clientId)
                    .getSingleResult();
            transaction.commit();
        }

        return client;
    }

    public static List<Client> getAllClients() {
        List<Client> clients;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            clients = session
                    .createQuery(
                            "SELECT c FROM client c " +
                                    "join fetch c.routes " +
                                    "WHERE c.deletedAt IS NULL", Client.class)
                    .getResultList();
            transaction.commit();
        }

        return clients;
    }

    public static List<Route> getAllRoutesByClient(long clientId) {
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
                                    "WHERE r.client.id = :clientId " +
                                    "AND r.deletedAt IS NULL",
                            Route.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
            transaction.commit();
        }

        return routes;
    }

    public static BigDecimal getClientDebt(long clientId) {
        Client client = getClientById(clientId);

        return client.getDebt();
    }

    public static String checkClientDebt(long clientId) {
        BigDecimal debt = getClientDebt(clientId);

        return debt == BigDecimal.ZERO
                ? "Client with id=" + clientId + " has NO debt."
                : "Client with id=" + clientId + " has " + debt + " leva debt.";
    }

    public static void updateClientName(UpdateClientDto updateClientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE client c SET c.name = :name " +
                                    "WHERE c.id = :id " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("name", updateClientDto.getName())
                    .setParameter("id", updateClientDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateClientDebt(UpdateClientDto updateClientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE client c " +
                                    "SET c.debt = c.debt + :newDebt " +
                                    "WHERE c.id = :id " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("newDebt", updateClientDto.getDebt())
                    .setParameter("id", updateClientDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateClient(UpdateClientDto updateClientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE client c " +
                                    "SET c.name = :newName, " +
                                    "c.debt = c.debt + :additionalDebt " +
                                    "WHERE c.id = :id " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("newName", updateClientDto.getName())
                    .setParameter("additionalDebt", updateClientDto.getDebt())
                    .setParameter("id", updateClientDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void clientPaysDebt(UpdateClientDto updateClientDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE client c " +
                                    "SET c.debt = c.debt - :newDebt " +
                                    "WHERE c.id = :id " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("newDebt", updateClientDto.getDebt())
                    .setParameter("id", updateClientDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void softDeleteClient(long clientId) {
        LocalDate deleteDate = LocalDate.now();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE client c SET c.deletedAt = :deleteDate " +
                                    "WHERE c.id = :id")
                    .setParameter("deleteDate", deleteDate)
                    .setParameter("id", clientId)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void hardDeleteClient(Client client) {
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }

}
