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
    }
}