package Services;

import java.util.List;
import java.util.Scanner;

import Entities.City;
import Repo.CityRepository;

public class CityService {

    private CityRepository repo;   // Creates the repository object.
    private static Scanner scan = new Scanner(System.in);

    public CityService(String driver, String url, String user, String pwd) {
        repo = new CityRepository(driver, url, user, pwd);
    }

    public List<City> getAllCities() {

        List<City> cities = repo.getAllCities();

        if (cities.size() == 0) { // if any record doesn't exists
            System.out.println("Sorry !!! there are no cities present now");
        }

        return cities;
    }

    public String insertCity() {

        System.out.println("City Id");
        int id = scan.nextInt();
        scan.nextLine(); // consume newline

        System.out.println("City Name");
        String name = scan.nextLine();

        System.out.println("City Country Code");
        String countryCode = scan.nextLine();

        System.out.println("District Name");
        String district = scan.nextLine();

        System.out.println("Population");
        double population = scan.nextDouble();
 
        City city = new City(id, name, countryCode, district, population); // creates and calls 

        return repo.saveCity(city);// to insert the record.
    }

    public City getCityById(int id) {

        City city = repo.getCityById(id);

        if (city == null) {
            System.out.println("Sorry !! there is no city found with the given Id.");
        }

        return city;
    }

    public String updateCity(int id) {

        City city = getCityById(id);

        if (city == null) {
            return "Sorry!!! The required city is not available."; //If no record exists,prints an error message.
        }

        System.out.println("Id : " + city.getId());
        System.out.println("Name : " + city.getName());
        System.out.println("Country Code : " + city.getCountrycode());
        System.out.println("District : " + city.getDistrict());
        System.out.println("Population : " + city.getPopulation());

        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Country Code");
        System.out.println("3. District");
        System.out.println("4. Population");

        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();
        scan.nextLine(); // consume newline

        switch (choice) {

        case 1:
            System.out.print("Enter new Name: ");
            city.setName(scan.nextLine());
            break;

        case 2:
            System.out.print("Enter new Country Code: ");
            city.setCountrycode(scan.nextLine());
            break;

        case 3:
            System.out.print("Enter new District: ");
            city.setDistrict(scan.nextLine());
            break;

        case 4:
            System.out.print("Enter new Population: ");
            city.setPopulation(scan.nextDouble());
            break;

        default:
            return "Invalid Choice";
        }

        return repo.updateCity(city);
    }
}