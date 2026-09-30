package Repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import DBConnect.Connect;
import Entities.City;

public class CityRepository extends Connect { // Calls the constructor of Connect.

	String query = "";
	private PreparedStatement ps;
	private ResultSet rs;

	public CityRepository(String driverClass, String url, String userName, String password) {
		super(driverClass, url, userName, password); // establishes the connection 

	}
    // Inserts a new city into the database.
	public String saveCity(City city) {
		String result = "";
		try {
			query = "insert into city(id,name,countrycode,district,population)";
			query = query.concat(" values(?,?,?,?,?)");

			ps = con.prepareStatement(query);

			ps.setInt(1, city.getId());
			ps.setString(2, city.getName());
			ps.setString(3, city.getCountrycode());
			ps.setString(4, city.getDistrict());
			ps.setDouble(5, city.getPopulation());

			ps.executeUpdate();

			result = "One City record has been inserted";

		} catch (Exception e) {
			result = "Unable to insert the city : " + e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	public List<City> getAllCities() {

		query = "select * from city";

		List<City> cities = new LinkedList<City>();

		try {
			ps = con.prepareStatement(query);

			cities = getAvailableCities(ps);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cities;
	}
   // Searches a city using its id.
	public City getCityById(int id) {

		City city = null;

		query = "select * from city where id=?";

		try {

			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				city = new City();

				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setCountrycode(rs.getString("countrycode"));
				city.setDistrict(rs.getString("district"));
				city.setPopulation(rs.getDouble("population"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return city;
	}
  
	public List<City> getAvailableCities(PreparedStatement ps) { // Common method for converting ResultSet into a List.

		List<City> cities = new LinkedList<City>();

		try {

			rs = ps.executeQuery();

			while (rs.next()) { // read one record at a time 

				City city = new City();  // Stores every column value.

				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setCountrycode(rs.getString("countrycode"));
				city.setDistrict(rs.getString("district"));
				city.setPopulation(rs.getDouble("population"));

				cities.add(city); // finally add to the list 

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cities;
	}
	 
	// Searches cities by name.

	public List<City> getAllCitiesByName(String name) {

		query = "select * from city where name=?";

		List<City> cities = new LinkedList<City>();

		try {

			ps = con.prepareStatement(query);

			ps.setString(1, name);

			cities = getAvailableCities(ps);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cities;
	}
    //Updates city information.
	public String updateCity(City city) {

		query = "update city set name=?,countrycode=?,district=?,population=? where id=?";

		String result = "";

		try {

			ps = con.prepareStatement(query);

			ps.setString(1, city.getName());
			ps.setString(2, city.getCountrycode());
			ps.setString(3, city.getDistrict());
			ps.setDouble(4, city.getPopulation());
			ps.setInt(5, city.getId());

			ps.executeUpdate();

			result = "Data has been updated successfully";

		} catch (Exception e) {

			result = "Unable to update data for the given object";

			e.printStackTrace();
		}

		return result;
	}

}