package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.ClientDao;
import org.example.dao.VehicleDao;
import org.example.dto.Client.CreateClientDto;
import org.example.dto.Vehicle.CreateVehicleDto;
import org.example.dto.Vehicle.UpdateVehicleDto;
import org.example.entity.Client;
import org.example.entity.Company;
import org.example.entity.Route;
import org.example.entity.Vehicle;
import org.example.enums.VehicleType;
import org.hibernate.HibernateException;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        try {
            SessionFactoryUtil.getSessionFactory().openSession();
        } catch(HibernateException e) {
            System.out.printf(e.getMessage());
        }

        // TESTING VEHICLE
        //Company company = new Company(1, LocalDate.now(), "TestCompany", "test description");
        //CreateVehicleDto createVehicleDto = new CreateVehicleDto("BT2663KM", VehicleType.SPECIAL_TRUCK, company);

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
        //END TESTING CLIENT
    }
}