package org.example.dao;

import org.example.configuration.SessionFactoryUtil;
import org.example.dto.Client.CreateClientDto;
import org.example.dto.Client.UpdateClientDto;
import org.example.dto.Company.CreateCompanyDto;
import org.example.dto.Company.UpdateCompanyDto;
import org.example.dto.Employee.CreateEmployeeDto;
import org.example.dto.Employee.UpdateEmployeeDto;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeeDao {

    public static void createEmployee(CreateEmployeeDto createEmployeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setName(createEmployeeDto.getName());
            employee.setSalary(createEmployeeDto.getSalary());
            employee.setCompany(createEmployeeDto.getCompany());
            employee.setCategory(createEmployeeDto.getCategory());
            employee.setCreatedAt(createEmployeeDto.getCompany().getCreatedAt());
            session.save(employee);

            transaction.commit();
        }
    }

    public static Employee getEmployeeById(long employeeId) {
        Employee employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee = session.createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "WHERE e.id = :employeeId " +
                                    "AND e.deletedAt IS NULL", Employee.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();
            transaction.commit();
        }
        return employee;
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> employees;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "WHERE e.deletedAt IS NULL", Employee.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }

    public static void updateEmployeeName(UpdateEmployeeDto updateEmployeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE employee e SET e.name = :name " +
                                    "WHERE e.id = :employeeId " +
                                    "AND e.deletedAt IS NULL")
                    .setParameter("name", updateEmployeeDto.getName())
                    .setParameter("employeeId", updateEmployeeDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateEmployeeSalary(UpdateEmployeeDto updateEmployeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE employee e SET e.salary = :salary " +
                                    "WHERE e.id = :employeeId " +
                                    "AND e.deletedAt IS NULL")
                    .setParameter("salary", updateEmployeeDto.getSalary())
                    .setParameter("employeeId", updateEmployeeDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateEmployeeCategory(UpdateEmployeeDto updateEmployeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE employee e SET e.category = :category " +
                                    "WHERE e.category = :employeeId " +
                                    "AND e.deletedAt IS NULL")
                    .setParameter("category", updateEmployeeDto.getSalary())
                    .setParameter("employeeId", updateEmployeeDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void updateEmployee(UpdateEmployeeDto updateEmployeeDto) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE employee e " +
                                    "SET e.name = :newName, " +
                                    "e.category = :newCategory, " +
                                    "e.salary = :newSalary " +
                                    "WHERE e.id = :employeeId" +
                                    "AND e.deletedAt IS NULL")
                    .setParameter("newName", updateEmployeeDto.getName())
                    .setParameter("newCategory", updateEmployeeDto.getCategory())
                    .setParameter("newProfit", updateEmployeeDto.getSalary())
                    .setParameter("employeeId", updateEmployeeDto.getId())
                    .executeUpdate();
            transaction.commit();
        }
    }

    public static void softDeleteEmployee(long employeeId) {
        LocalDate deleteDate = LocalDate.now();
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(
                            "UPDATE employee e SET e.deletedAt = :deleteDate " +
                                    "WHERE c.id = :employeeId")
                    .setParameter("deleteDate", deleteDate)
                    .setParameter("employeeId", employeeId)
                    .executeUpdate();
            transaction.commit();
        }
    }
    public static void hardDeleteCompany(Employee employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
