package org.example.dao;
import org.example.configuration.SessionFactoryUtil;
import org.example.entity.Company;
import org.example.enums.SortTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.List;

public class CompaniesSortAndFilterDao {
    public static List<Company> sortCompaniesAlphabetically() {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM company c " +
                                    "join fetch c.employees " +
                                    "join fetch c.vehicles " +
                                    "join fetch c.routes " +
                                    "WHERE c.deletedAt IS NULL " +
                                    "ORDER BY c.name ASC", Company.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public static List<Company> sortCompaniesByProfit(SortTypes sort) {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM company c " +
                                    "join fetch c.employees " +
                                    "join fetch c.vehicles " +
                                    "join fetch c.routes " +
                                    "WHERE c.deletedAt IS NULL " +
                                    "ORDER BY c.profit " + sort, Company.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public static List<Company> sortCompaniesByProfitAlphabetically(SortTypes sort) {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM company c " +
                                    "join fetch c.employees " +
                                    "join fetch c.vehicles " +
                                    "join fetch c.routes " +
                                    "WHERE c.deletedAt IS NULL " +
                                    "ORDER BY c.profit " + sort + ", c.name ASC", Company.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public static List<Company> showCompaniesWithProfitGreaterThan(BigDecimal profit) {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM company c " +
                                    "join fetch c.employees " +
                                    "join fetch c.vehicles " +
                                    "join fetch c.routes " +
                                    "WHERE c.profit >= :profit " +
                                    "AND c.deletedAt IS NULL ", Company.class)
                    .setParameter("profit", profit)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public static List<Company> showCompaniesWithProfitLessThan(BigDecimal profit) {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM company c " +
                                    "join fetch c.employees " +
                                    "join fetch c.vehicles " +
                                    "join fetch c.routes " +
                                    "WHERE c.profit < :profit " +
                                    "AND c.deletedAt IS NULL ", Company.class)
                    .setParameter("profit", profit)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }

    public static List<Company> showCompaniesWithProfit() {
        return showCompaniesWithProfitGreaterThan(BigDecimal.ZERO);
    }
    
    public static List<Company> filterCompaniesByName(String nameSubstring) {
        List<Company> companies;
        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companies = session
                    .createQuery(
                            "SELECT c FROM company c " +
                                    "join fetch c.employees " +
                                    "join fetch c.vehicles " +
                                    "join fetch c.routes " +
                                    "WHERE c.name LIKE  %" + nameSubstring +"% " +
                                    "AND c.deletedAt IS NULL " +
                                    "ORDER BY c.name ASC, c.profit ASC ", Company.class)
                    .getResultList();
            transaction.commit();
        }
        return companies;
    }
}
