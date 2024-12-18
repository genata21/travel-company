package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.*;
import org.example.dto.Client.CreateClientDto;
import org.example.dto.Client.UpdateClientDto;
import org.example.dto.Company.CreateCompanyDto;
import org.example.dto.Employee.CreateEmployeeDto;
import org.example.dto.Route.CreateRouteDto;
import org.example.dto.Vehicle.CreateVehicleDto;
import org.example.dto.Vehicle.UpdateVehicleDto;
import org.example.entity.*;
import org.example.enums.CargoType;
import org.example.enums.Category;
import org.example.enums.VehicleType;
import org.hibernate.HibernateException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        try {
            SessionFactoryUtil.getSessionFactory().openSession();
        } catch (HibernateException e) {
            System.out.printf(e.getMessage());
        }

        // TESTING VEHICLE
//        Company company = new Company(1, LocalDate.now(), "TestCompany", "test description");
//        CreateVehicleDto createVehicleDto = new CreateVehicleDto("BT2663KM", VehicleType.SPECIAL_TRUCK, company);

//        VehicleDao.createVehicle(createVehicleDto);
//        Vehicle vehicle = VehicleDao.getVehicleById(4);
//        List<Vehicle> vehicles = VehicleDao.getAllVehicles();
//        System.out.println(vehicles);
//        List<Vehicle> vehicles = VehicleDao.getCompanyVehicles(2);
//        List<Route> routes = VehicleDao.getAllRoutesDoneByVehicle(2);
//        System.out.println(routes);
//        UpdateVehicleDto updateVehicleDto = new UpdateVehicleDto(2, "BT1413BX");
//        VehicleDao.updateVehiclePlate(updateVehicleDto);

//        VehicleDao.softDeleteVehicle(2);
//        VehicleDao.hardDeleteCompany(vehicle);
        //END TESTING VEHICLE

        //TESTING CLIENT
//        CreateClientDto createClientDto = new CreateClientDto("Simona");
//        ClientDao.createClient(createClientDto);

//        Client client = ClientDao.getClientById(1);
//        System.out.println(client);

//        List<Client> clients = ClientDao.getAllClients();
//        System.out.println(clients);

//        List<Route> routes = ClientDao.getAllRoutesByClient(1);
//        System.out.println(routes);

//        BigDecimal debt = ClientDao.getClientDebt(1);
//        System.out.println(debt);

//        UpdateClientDto updateClientDto = new UpdateClientDto(1, "Evgeni Ignatov", BigDecimal.TWO);
//        ClientDao.updateClient(updateClientDto);

//        UpdateClientDto updateClientDto = new UpdateClientDto(1, BigDecimal.TWO);
//        ClientDao.clientPaysDebt(updateClientDto);

//        UpdateClientDto updateClientDto = new UpdateClientDto(1,"Evgeni", BigDecimal.TEN);
//        ClientDao.updateClient(updateClientDto);
//
//        String message = ClientDao.checkClientDebt(1);
//        System.out.println(message);

//        ClientDao.softDeleteClient(2);

//        Client client = ClientDao.getClientById(3);
//        System.out.println(client);
//        ClientDao.hardDeleteClient(client);
        //END TESTING CLIENT

        //RouteDao.saveToFile("file.txt");
        //RouteDao.loadFromFile("textNew.txt");

//------------------------------------------------------------------------//

        //total number of routes
        //long totalRoutes = RouteDao.getTotalRoutes();
        //System.out.println(totalRoutes);

        //total revenue
        //BigDecimal totalRevenue = RouteDao.getTotalRevenue();
        //System.out.println(totalRevenue);

        //count of routes by each driver (Employee)
//        List<String> employeeRoutes = RouteDao.getRoutesByEmployee();
//
//        System.out.println("Routes by Employee:");
//        for (String route : employeeRoutes) {
//            System.out.println(route);
//        }

        //revenue by each driver (Employee)
//        List<String> employeeRevenue = RouteDao.getRevenueByEmployee();
//        for (String revenue : employeeRevenue) {
//            System.out.println(revenue);
//        }

    // revenue for a specific period
        //LocalDate startDate = LocalDate.of(2025,12,11);
        //LocalDate endDate = LocalDate.of(2025,12,11);
        //BigDecimal revenue = RouteDao.getRevenueForPeriod(startDate, endDate);
        //System.out.println("Total revenue for the period: " + revenue);
    }
}
