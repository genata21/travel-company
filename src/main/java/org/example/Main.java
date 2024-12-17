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

//        CreateCompanyDto newComp = new CreateCompanyDto("enigma", "hotels");
//        CreateCompanyDto newComp2 = new CreateCompanyDto("lidl", "foods", BigDecimal.ONE);
//        System.out.println(newComp);

//        Company company1 = new Company(1, LocalDate.now(), "Enigma", "hotels", BigDecimal.valueOf(4));
//        Vehicle vehicle1 = new Vehicle(1, LocalDate.now(), "VT 2108 KT", VehicleType.BUS, company1);
//         Employee employee1 = new Employee(1, LocalDate.now(), "Evgeni", Category.BUS_DRIVER, BigDecimal.valueOf(3), company1);
//         Client client1 = new Client(1, LocalDate.now(), "Petko", BigDecimal.valueOf(8));
//        Route route1 = new Route(
//                1,
//                LocalDate.now(),
//                "plovdiv",
//                "sofia",
//                LocalDate.now(),
//                LocalDate.now(),
//                CargoType.FUEL,
//                false,
//                BigDecimal.valueOf(1),
//                BigDecimal.valueOf(2),
//                vehicle1,
//                company1,
//                employee1,
//                client1);
//         CreateCompanyDto newComp = new CreateCompanyDto("Enigma", "hotels", BigDecimal.valueOf(3));
//       CompanyDao.createCompany(newComp);
//
//       CreateVehicleDto newV = new CreateVehicleDto("VT 2108 KT",VehicleType.BUS,company1);
//       VehicleDao.createVehicle(newV);
//
//        CreateEmployeeDto newE = new CreateEmployeeDto("Ivan", Category.BUS_DRIVER,BigDecimal.valueOf(1000),company1);
//        EmployeeDao.createEmployee(newE);
//
//        CreateClientDto newCl = new CreateClientDto("Evgeni");
//        ClientDao.createClient(newCl);


//        RouteDao.CreateRoute(newR);
        //RouteDao.saveToFile("file1.txt");
        RouteDao.loadFromFile();

        //System.out.println("Current working directory: " + System.getProperty("user.dir"));

//
//        System.out.println("Client: " + client1);
//        System.out.println("Route DTO: " + newR);
//    }
    }
}
