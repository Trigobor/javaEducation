package org.example;

import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;
import org.example.services.CitizenService;
import org.example.services.CityService;
import org.example.services.CountryService;

import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CitizenService citizenService = new CitizenService();
        CityService cityService = new CityService();
        CountryService countryService = new CountryService();

        Country latveria = new Country("Latveria");
        countryService.saveCountry(latveria);

        City doomstadt = new City("Doomstadt");
        doomstadt.setCountry(latveria);
        latveria.addCity(doomstadt);
        cityService.saveCity(doomstadt);

        City doomsburg = new City("Doomsburg");
        doomsburg.setCountry(latveria);
        latveria.addCity(doomsburg);
        cityService.saveCity(doomsburg);

        Citizen victor = new Citizen("Victor von Doom", doomstadt);
        Citizen boris = new Citizen("Boris", doomstadt);
        Citizen kristoff = new Citizen("Kristoff Vernard", doomstadt);
        Citizen valeria = new Citizen("Valeria", doomsburg);
        Citizen caroline = new Citizen("Caroline le Fey", doomsburg);
        citizenService.saveCitizen(victor);
        citizenService.saveCitizen(boris);
        citizenService.saveCitizen(kristoff);
        citizenService.saveCitizen(valeria);
        citizenService.saveCitizen(caroline);

        countryService.getCitizens(latveria).stream().forEach(citizen -> System.out.println(citizen.getName()));

        latveria.getCities().stream().forEach(city -> System.out.println(city.getName()));

        System.out.println(victor.getCity().getCountry().getName());
    }
}