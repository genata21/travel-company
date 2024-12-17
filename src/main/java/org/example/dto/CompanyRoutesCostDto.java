package org.example.dto;
import java.math.BigDecimal;
public class CompanyRoutesCostDto {
    private String companyName;
    private BigDecimal routesCost;
    public CompanyRoutesCostDto(String companyName, BigDecimal routeCost) {
        this.companyName = companyName;
        this.routesCost = routeCost;
    }
    public String getCompanyName() { return companyName; }
    public BigDecimal getRoutesCost() { return routesCost; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setRoutesCost(BigDecimal routesCost) { this.routesCost = routesCost; }
    @Override
    public String toString() {
        return companyName + ", routesCost: " + routesCost;
    }
}