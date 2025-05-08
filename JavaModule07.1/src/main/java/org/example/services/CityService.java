package org.example.services;

import org.example.DAO.CityDAO;
import org.example.models.Citizen;
import org.example.models.City;

import java.util.List;
import java.util.function.Supplier;

public class CityService {
    private final CityDAO cityDAO;
    private final Supplier<CitizenService> citizenServiceSupplier;
    private final Supplier<CountryService> countryServiceSupplier;

    public CityService(CityDAO cityDAO, Supplier<CitizenService> citizenServiceSupplier, Supplier<CountryService> countryServiceSupplier) {
        this.cityDAO = cityDAO;
        this.citizenServiceSupplier = citizenServiceSupplier;
        this.countryServiceSupplier = countryServiceSupplier;
    }

    public City findByID(int id) {
        return cityDAO.findById(id);
    }

    public City findByID(int id, boolean withCitizens) {
        if (withCitizens) {
            return cityDAO.findById(id);
        }
        return cityDAO.findById(id, false);
    }

    public void saveCity(City City) {
        cityDAO.save(City);
    }

    public City createCity(String cityName, int countryID) {
        return cityDAO.createCity(cityName, countryID);
    }

    public void updateCity(City City) {
        cityDAO.update(City);
    }

    public void deleteCity(City City) {
        cityDAO.delete(City);
    }

    public List<City> findAllCitys() {
        return cityDAO.findAll();
    }
}
