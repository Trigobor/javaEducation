package org.example.services;

import org.example.DAO.CitizenDAO;
import org.example.models.Citizen;

import java.util.List;

public class CitizenService {

    private CitizenDAO citizenDAO = new CitizenDAO();

    public CitizenService() {}

    public Citizen findByID(int id) {
        return citizenDAO.findById(id);
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
