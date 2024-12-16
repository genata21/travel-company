package org.example.dao;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Employee;
import org.example.enums.Category;
import org.example.enums.SortTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.List;
public class EmployeesSortAndFilterDao {
    public static List<Employee> sortEmployeeBySalary(SortTypes sort) {
        List<Employee> employees;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "join fetch e.vehicles " +
                                    "join fetch e.routes " +
                                    "WHERE e.deletedAt IS NULL " +
                                    "ORDER BY e.salary " + sort, Employee.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }
    public static List<Employee> showCEmployeesWithSalaryGreaterThan(BigDecimal salary) {
        List<Employee> employees;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "join fetch e.vehicles " +
                                    "join fetch e.routes " +
                                    "WHERE e.salary >= :salary " +
                                    "AND e.deletedAt IS NULL ", Employee.class)
                    .setParameter("salary", salary)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }
    public static List<Employee> showEmployeeWithSalaryLessThan(BigDecimal salary) {
        List<Employee> employees;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "join fetch e.vehicles " +
                                    "join fetch e.routes " +
                                    "WHERE e.salary < :salary " +
                                    "AND e.deletedAt IS NULL ", Employee.class)
                    .setParameter("salary", salary)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }
    public static List<Employee> sortEmployeeByCategoryAlphabetically(Category category) {
        List<Employee> employees;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "join fetch e.vehicles " +
                                    "join fetch e.routes " +
                                    "WHERE e.deletedAt IS NULL " +
                                    "ORDER BY e.category ASC " , Employee.class)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }
    public static List<Employee> sortEmployeeByCategory(Category category) {
        List<Employee> employees;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = session
                    .createQuery(
                            "SELECT e FROM employee e " +
                                    "join fetch e.company " +
                                    "join fetch e.vehicles " +
                                    "join fetch e.routes " +
                                    "WHERE e.category = :category " +
                                    "AND e.deletedAt IS NULL ", Employee.class)
                    .setParameter("category", category)
                    .getResultList();
            transaction.commit();
        }
        return employees;
    }
}