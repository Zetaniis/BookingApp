package swing.sample;

import org.apache.log4j.Logger; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;

public class sqliteDB {
	Connection conn = null;
	Statement stat = null;
	private static Logger log = Logger.getLogger(base.class.getName());	
	
	sqliteDB(){
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:./Properties/FlightDB.sqlite");	
			stat = conn.createStatement();
			log.debug("Connection to databases is established");
			//closeConnection();
		}
		catch(Exception e) {
			log.error(e.getMessage(), e);
			System.exit(1);
		}
	}
	
	//Closing databases:
	public void closeConnection() {
		try {
			conn.close();
			log.debug("Connection to databases was closed");
		}
		catch(SQLException ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	// tobe changed
	public void showFlightList() {
		try {			
			ResultSet rs = stat.executeQuery("select * from FlightSource");
			// add query to show list of flights
			while(rs.next()) {
				String name = rs.getString("IATA");
				System.out.println(name);
			}
			stat.close();
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	// show data from BookingList table:
	public void showBookingList() {
		try {
			ResultSet rs = stat.executeQuery("select * from BookingList");
			while(rs.next()) {
				int id = rs.getInt("userID");
				String date = rs.getString("date");
				String source = rs.getString("source");
				String destination = rs.getString("destination");
				System.out.println(id + " " + date + " " + source + " " + destination);
			}						
			stat.close();
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	// Booking ticket:
	public void bookingTicket(String name, String secondName, String lastName, String email, int ticketAmount, int newsLetter, String from, String to, String date, String time) {
		try{
			// add data to Clients table
			String dbquery1 = "INSERT INTO Clients (name, secondName, lastName, email, ticketAmount, newsletter)" + "VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstat = conn.prepareStatement(dbquery1);
			pstat.setString(1, name);
			pstat.setString(2, secondName);
			pstat.setString(3, lastName);
			pstat.setString(4, email);
			pstat.setInt(5, ticketAmount);
			pstat.setInt(6, newsLetter);
			pstat.executeUpdate();
			
			// add data to BookingList table
			ResultSet rs = stat.executeQuery("select max(userID) from Clients");
			int id = rs.getInt("userID");
			stat.close();
			String dbquery2 = "INSERT INTO BookingList (userID, date, time, source, destination" + "VALUES (?, ?, ?, ?, ?)";
			pstat = conn.prepareStatement(dbquery2);
			pstat.setInt(1, id);
			pstat.setString(2, date);
			pstat.setString(3, time);
			pstat.setString(4, from);
			pstat.setString(5, to);
			pstat.executeUpdate();
		}
		catch(SQLException e){
			log.error(e.getMessage(), e);
		}
		
	}
	
	// Delete ticker order from BookingList table
	public void cancelTicket(int num) {
		try {
			String query = "DELETE FROM BookingList WHERE userID = " + num;
			
			stat.executeUpdate(query);
			stat.close();
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	// add flights (admin only) add data to FlightSource, FlightDestination, Aircraft tables
	public void addFlight(String regNur, String date, String time, String from, String to, String airline, String model) {
		try {
			// add to FlightSource table:
			String query1 = "INSERT INTO FlightSource (aircraftRegNr, date, time, IATA)" + "VALUES (?, ?, ?, ?)";
			PreparedStatement pstat = conn.prepareStatement(query1);
			pstat.setString(1, regNur);
			pstat.setString(2, date);
			pstat.setString(3, time);
			pstat.setString(4, from);
			pstat.executeUpdate();		
			
			// add to FlightDestination table:
			String query2 = "INSERT INTO FlightDestination (aircraftRegNr, date, time, IATA)" + "VALUES (?, ?, ?, ?)";
			pstat = conn.prepareStatement(query2);
			pstat.setString(1, regNur);
			pstat.setString(2, date);
			pstat.setString(3, time);
			pstat.setString(4, to);
			pstat.executeUpdate();
			
			// add to Aircraft table:
			String query3 = "INSERT INTO Aircraft (aircraftRegNr, airlines, modelName)" + "VALUES (?, ?, ?)";
			pstat = conn.prepareStatement(query3);
			pstat.setString(1, regNur);
			pstat.setString(2, airline);
			pstat.setString(2, model);
			pstat.executeUpdate();
			
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}	
	}
	
	// remove flights: delete data from FlightSource, FlightDestination
	public void removeFlight(String regNum) {
		try {
			String query1 = "DELETE FROM FlightSource WHERE aircraftRegNr = " + regNum;
			String query2 = "DELETE FROM FlightDestination WHERE aircraftRegNr = " + regNum;
			stat.executeUpdate(query1);
			stat.executeUpdate(query2);
			stat.close();						
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	
}
