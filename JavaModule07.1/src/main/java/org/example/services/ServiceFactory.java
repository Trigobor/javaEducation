package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.DAO.CitizenDAO;
import org.example.DAO.CityDAO;
import org.example.DAO.CountryDAO;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class ServiceFactory {
    private CityDAO cityDAO;
    private CitizenDAO citizenDAO;
    private CountryDAO countryDAO;

    public ServiceFactory() {
        this.cityDAO = new CityDAO();
        this.citizenDAO = new CitizenDAO();
        this.countryDAO = new CountryDAO();
    }

    public CityService createCityService() {
        return new CityService(cityDAO, this::createCitizenService, this::createCountryService);
    }

    public CitizenService createCitizenService() {
        return new CitizenService(citizenDAO, this::createCityService, this::createCountryService);
    }

    public CountryService createCountryService() {
        return new CountryService(countryDAO, this::createCityService, this::createCitizenService);
    }
}