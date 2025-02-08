package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.DAO.CitizenDAO;
import org.example.DAO.CountryDAO;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CountryService {

    private final CountryDAO countryDAO;
    private final Supplier<CityService> cityServiceSupplier;
    private final Supplier<CitizenService> citizenServiceSupplier;

    public CountryService(CountryDAO countryDAO, Supplier<CityService> cityServiceSupplier, Supplier<CitizenService> citizenServiceSupplier) {
        this.countryDAO = countryDAO;
        this.cityServiceSupplier = cityServiceSupplier;
        this.citizenServiceSupplier = citizenServiceSupplier;
    }

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

    public List<City> findAllCities(Country country) {
        return new ArrayList<City>(country.getCities());
    }

    public List<Citizen> getCitizens(int countryID) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            //for (City city : cities) {
            //       // Загружаем граждан для каждого города
            //       List<Citizen> cityCitizens = entityManager.createQuery("SELECT c FROM Citizen c WHERE c.city.id = :cityId", Citizen.class)
            //               .setParameter("cityId", city.getId())
            //               .getResultList();
            //
            //       // Добавляем найденных граждан в общий список
            //       citizens.addAll(cityCitizens);
            //   }
            // Вот так как-нибудь выглядела бы моя N+1 SELECT ошибка.
            // Сам я этот код до ума не доводил и не запускал, написал просто для того,
            // чтобы продемонстрировать понимание ошибки N+1 SELECT

            List<Citizen> citizens = session.createQuery("SELECT c FROM Citizen c JOIN FETCH c.city WHERE c.city.country.id = :countryId", Citizen.class)
                    .setParameter("countryId", countryID)
                    .getResultList();
            return citizens;
        }
    }
}
