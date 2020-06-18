package swing.sample;

import org.apache.log4j.Logger; 
import java.sql.Connection;
import java.sql.DriverManager;
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
			log.debug("Connection to databases is established");
			//closeConnection();
		}
		catch(Exception e) {
			log.error(e.getMessage(), e);
			System.exit(1);
		}
	}
	
	public void closeConnection() {
		try {
			conn.close();
			log.debug("Connection to databases was closed");
		}
		catch(SQLException ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	public void showFlightList() {
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from FlightSource");
			// add query to show list of flights
			while(rs.next()) {
				String name = rs.getString("IATA");
				System.out.println(name);
			}
		}
		catch(SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
}
