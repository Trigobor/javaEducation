package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.DAO.CitizenDAO;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CitizenService {

    private CitizenDAO citizenDAO = new CitizenDAO();

    public CitizenService() {}

    public Citizen findByID(int id) {
        return citizenDAO.findById(id);
    }

    public Citizen createCitizen(String citizenName, int cityID) {
        return citizenDAO.createCitizen(citizenName, cityID);
    }

    public void addCitizenToCity(int citizenID, int cityID) {
        Citizen citizen = citizenDAO.findById(citizenID);
        City city = null;

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<City> query = session.createQuery("from City where id = :id", City.class);
            query.setParameter("id", cityID);
            city = query.getSingleResult();
        }

        if (city == null) {
            throw new EntityNotFoundException("City with id " + cityID + " not found.");
        }
        citizen.setCity(city);
        citizenDAO.update(citizen);
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
