package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Company.CreateCompanyDto;
import org.example.dto.Company.UpdateCompanyDto;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Route;
import org.example.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CompanyDao {

    public static void createCompany(CreateCompanyDto createCompanyDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Company company = new Company();
            company.setName(createCompanyDto.getName());
            company.setDescription((createCompanyDto.getDescription()));
            company.setProfit(BigDecimal.ZERO);

            session.save(company);
            transaction.commit();
        }
    }

    public static Company getCompanyById(long companyId) {
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            company = session.get(Company.class, companyId);
            transaction.commit();
        }

        return company;
    }

    public static List<Company> getAllCompanies() {
        List<Company> companies;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM Company c ", Company.class)
                    .getResultList();
            transaction.commit();
        }

        return companies;
    }

    public static List<Employee> getAllEmployeesInCompany(long companyId) {
        List<Employee> employees;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM Employee e " +
                                    "join e.company"  +
                                    "WHERE e.id = :id " +
                                    "AND e.deletedAt IS NULL",
                            Employee.class)
                    .setParameter("id", companyId)
                    .getResultList();
            transaction.commit();
        }

        return employees;
    }

    public static List<Vehicle> getAllVehiclesInCompany(long companyId) {
        List<Vehicle> vehicles;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicles = session
                    .createQuery(
                            "SELECT v FROM Vehicle v " +
                                    "join v.company"  +
                                    "WHERE v.id = :id " +
                                    "AND v.deletedAt IS NULL",
                            Vehicle.class)
                    .setParameter("id", companyId)
                    .getResultList();
            transaction.commit();
        }

        return vehicles;
    }

    public static List<Route> getAllRoutesInCompany(long companyId) {
        List<Route> routes;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            routes = session
                    .createQuery(
                            "SELECT r FROM route r " +
                                    "join fetch r.company " +
                                    "WHERE r.id = :id " +
                                    "AND r.deletedAt IS NULL",
                            Route.class)
                    .setParameter("id", companyId)
                    .getResultList();
            transaction.commit();
        }

        return routes;
    }

    public static BigDecimal getCompanyProfit(long companyId) {
        Company company = getCompanyById(companyId);

        return company.getProfit();
    }

    public static void updateCompanyName(UpdateCompanyDto updateCompanyDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE company c SET c.name = :name " +
                                    "WHERE c.id = :id " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("name", updateCompanyDto.getName())
                    .setParameter("id", updateCompanyDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateCompanyProfit(UpdateCompanyDto updateCompanyDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE Company c " +
                                    "SET c.profit = c.profit + :newProfit " +
                                    "WHERE c.id = :id " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("newProfit", updateCompanyDto.getProfit())
                    .setParameter("id", updateCompanyDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateCompany(UpdateCompanyDto updateCompanyDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE Company c " +
                                    "SET c.name = :newName, " +
                                    "c.description = :newDescription " +
                                    "WHERE c.profit = c.profit + :newProfit " +
                                    "AND c.deletedAt IS NULL")
                    .setParameter("newName", updateCompanyDto.getName())
                    .setParameter("newDescription", updateCompanyDto.getDescription())
                    .setParameter("newProfit", updateCompanyDto.getProfit())
                    .setParameter("id", updateCompanyDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void softDeleteCompany(long companyId) {
        LocalDate deleteDate = LocalDate.now();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE company c SET c.deletedAt = :deleteDate " +
                                    "WHERE c.id = :id")
                    .setParameter("deleteDate", deleteDate)
                    .setParameter("id", companyId)
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void hardDeleteCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }
}

/** set route **/






