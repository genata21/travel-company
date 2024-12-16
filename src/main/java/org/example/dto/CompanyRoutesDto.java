package org.example.dto;
public class CompanyRoutesDto {
    private String companyName;
    private long routesCount;
    public CompanyRoutesDto(String companyName, long routesCount) {
        this.companyName = companyName;
        this.routesCount = routesCount;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public long getRoutesCount() {
        return routesCount;
    }
    public void setRoutesCount(long routesCount) {
        this.routesCount = routesCount;
    }
    @Override
    public String toString() {
        return companyName + ", routes: " + routesCount;
    }
}