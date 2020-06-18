package swing.sample;

import org.apache.log4j.Logger; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			ResultSet rs = stat.executeQuery("select * from Clients");
			// add query to show list of flights
			while(rs.next()) {
				String name = rs.getString("name");
				System.out.println(name);
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
	
	
}
