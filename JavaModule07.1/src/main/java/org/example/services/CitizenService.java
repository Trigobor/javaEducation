package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.DAO.CitizenDAO;
import org.example.models.Citizen;
import org.example.models.City;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    public Citizen findByID(int id, boolean withCity) {
        return citizenDAO.findById(id, withCity);
    }

    public Citizen createCitizen(String citizenName, int cityID, int salary, String citizenship) {
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
        updateCitizen(citizen);
        System.out.println("Гражданин " + citizen.getName() + " добавлен в город " + city.getName());
    }

    public Set<Citizen> findWealthyTownsfolk(Citizen citizen) {
        CityService cityService = cityServiceSupplier.get();
        City city = cityService.findByID(citizen.getCity().getId());

        if (city == null) {
            throw new EntityNotFoundException("Город не найден для горожанина " + citizen.getName());
        }

        Set<Citizen> citizens = city.getCitizens();
        if (citizens == null) {
            throw new EntityNotFoundException("Список жителей отсутствует в городе " + city.getName());
        }

        return citizens.stream()
                .filter(c -> c.getSalary() > 10000 && "LOCAL".equals(c.getCitizenship()))
                .collect(Collectors.toSet());
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
