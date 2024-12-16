package org.example.dao;
import org.example.configuration.SessionFactoryUtil;
import org.example.dto.CompanyRoutesDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
public class ReportsDao {
    public static List<CompanyRoutesDto> getRoutesCountPerCompany() {
        List<CompanyRoutesDto> result;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            result = session.createQuery(
                            "SELECT new org.example.dto.CompanyRoutesDto(c.name, COUNT(r)) " +
                                    "FROM company c " +
                                    "JOIN c.routes r " +
                                    "WHERE r.deletedAt IS NULL " +
                                    "AND c.deletedAt IS NULL " +
                                    "GROUP BY c.name " +
                                    "ORDER BY c.name, COUNT(r) DESC", CompanyRoutesDto.class)
                    .getResultList();
            transaction.commit();
        }
        return result;
    }
    public static void showRoutesPerCompany() {
        List<CompanyRoutesDto> companyRoutes = getRoutesCountPerCompany();
        long totalRoutes = companyRoutes.stream()
                .mapToLong(CompanyRoutesDto::getRoutesCount)
                .sum();
        System.out.println("Company routes: ");
        for(CompanyRoutesDto entry : companyRoutes) {
            System.out.println(entry);
        }
        System.out.println("-----------------");
        System.out.println("Total: " + totalRoutes);
    }
}