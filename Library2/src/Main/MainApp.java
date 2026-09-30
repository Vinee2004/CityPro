package Main;


import java.util.List;
import java.util.Scanner;

import Entities.City;
import Services.CityService;

public class MainApp {

	public static void main(String[] args) {

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/world";   
		String user = "root";                               
		String password = "root";                         

		CityService service = new CityService(driver, url, user, password);

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("\n===== CITY MANAGEMENT SYSTEM =====");
			System.out.println("1. Insert City");
			System.out.println("2. Display All Cities");
			System.out.println("3. Search City By Id");
			System.out.println("4. Update City");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println(service.insertCity());
				break;

			case 2:
				List<City> cities = service.getAllCities();
				for (City city : cities) {
					System.out.println(city);
				}
				break;

			case 3:
				System.out.print("Enter City Id: ");
				int id = sc.nextInt();
				System.out.println(service.getCityById(id));
				break;

			case 4:
				System.out.print("Enter City Id to update: ");
				int updateId = sc.nextInt();
				System.out.println(service.updateCity(updateId));
				break;

			case 5:
				System.out.println("Thank You...");
				sc.close();
				System.exit(0);

			default:
				System.out.println("Invalid Choice");
			}
		}
	}
}


