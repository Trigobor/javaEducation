package org.example;

import org.example.enums.CitizenshipStatus;
import org.example.models.Citizen;
import org.example.models.City;
import org.example.models.Country;
import org.example.services.CitizenService;
import org.example.services.CityService;
import org.example.services.CountryService;
import org.example.services.ServiceFactory;

import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ServiceFactory serviceFactory = new ServiceFactory();
        CitizenService citizenService = serviceFactory.createCitizenService();
        CityService cityService = serviceFactory.createCityService();
        CountryService countryService = serviceFactory.createCountryService();

        List<Citizen> citizens = countryService.getCitizens(1);
        for (Citizen citizen : citizens) {
            System.out.println(citizen.getName());
        }

        Citizen wolverine = citizenService.createCitizen("James Howlet", 4, 0, CitizenshipStatus.DUALCITIZENSHIP);

        System.out.println(wolverine.getName() + " now in " + wolverine.getCity().getName());

        citizenService.addCitizenToCity(wolverine.getId(), 3);

        System.out.println(wolverine.getName() + " now in " + wolverine.getCity().getName());
    }
}