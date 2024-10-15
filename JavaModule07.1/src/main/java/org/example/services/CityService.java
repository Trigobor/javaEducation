package org.example.services;

import org.example.DAO.CityDAO;
import org.example.models.Citizen;
import org.example.models.City;

import java.util.List;

public class CityService {

    private CityDAO cityDAO = new CityDAO();

    public CityService() {}

    public City findByID(int id) {
        return cityDAO.findById(id);
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
