package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.DAO.CitizenDAO;
import org.example.enums.CitizenshipStatus;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Supplier;

public class CitizenService {

    private final CitizenDAO citizenDAO;
    private final Supplier<CityService> cityServiceSupplier;
    private final Supplier<CountryService> countryServiceSupplier;

    public CitizenService(CitizenDAO citizenDAO, Supplier<CityService> cityServiceSupplier, Supplier<CountryService> countryServiceSupplier) {
        this.citizenDAO = citizenDAO;
        this.cityServiceSupplier = cityServiceSupplier;
        this.countryServiceSupplier = countryServiceSupplier;
    }

    public Citizen findByID(int id) {
        return citizenDAO.findById(id);
    }

    public Citizen createCitizen(String citizenName, int cityID, int salary, CitizenshipStatus citizenship) {
        return citizenDAO.createCitizen(citizenName, cityID, salary, citizenship);
    }

    public void addCitizenToCity(int citizenID, int cityID) {
        CityService cityService = cityServiceSupplier.get();
        City city;
        Citizen citizen;

        try {
            city = cityService.findByID(cityID);
        } catch (Exception e) {
            throw new EntityNotFoundException("Город с ID " + cityID + " не найден.");
        }
        try {
            citizen = this.findByID(citizenID);
        } catch (Exception e) {
            throw new EntityNotFoundException("Гражданин с ID " + citizenID + " не найден.");
        }

        city.addCitizen(citizen);
        cityService.updateCity(city);
        saveCitizen(citizen);
        System.out.println("Гражданин " + citizen.getName() + " добавлен в город " + city.getName());
    }

    public void saveCitizen(Citizen citizen) {
        citizenDAO.save(citizen);
    }

    public void updateCitizen(Citizen citizen) {
        citizenDAO.update(citizen);
    }

    public void deleteCitizen(Citizen citizen) {
        citizenDAO.delete(citizen);
    }

    public List<Citizen> findAllCitizens() {
        return citizenDAO.findAll();
    }
}
