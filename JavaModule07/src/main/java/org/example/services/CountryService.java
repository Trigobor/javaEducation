package org.example.services;

import org.example.DAO.CountryDAO;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryService {

    private CountryDAO countryDAO = new CountryDAO();

    public CountryService() {}

    public Country findByID(int id) {
        return countryDAO.findById(id);
    }

    public void saveCountry(Country Country) {
        countryDAO.save(Country);
    }

    public void updateCountry(Country Country) {
        countryDAO.update(Country);
    }

    public void deleteCountry(Country Country) {
        countryDAO.delete(Country);
    }

    public List<Country> findAllCountries() {
        return countryDAO.findAll();
    }

    public Country findCountryById(int id) {
        return countryDAO.findCountryById(id);
    }

    public List<City> findAllCities(Country country) {
        return new ArrayList<City>(country.getCities());
    }

    public List<Citizen> getCitizens(Country country) {
        return country.getCities().stream()
                .flatMap(city -> city.getCitizens().stream())
                .collect(Collectors.toList());
    }
}
