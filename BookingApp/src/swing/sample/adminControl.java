package swing.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class adminControl extends JFrame{
	
	private static Logger log = Logger.getLogger(base.class.getName());
	private JPanel contentPane;
	private JList<String> flightsList;
	private JList<String> datesList;
	private JLabel lblFlights;
	private JLabel lblDates;
	
	sqliteDB db;
	//static base ref1 = null;
	
	public List<String> sqlDates = new ArrayList<String>();
	public List<String> sqlFlights = new ArrayList<String>();
	public List<Integer> sqlFlight_id = new ArrayList<Integer>();
	public List<Integer> sqlBooking_id = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		
		EventQueue.invokeLater(new Runnable() {			
			public void run() {
				try {
					//ref1 = new base("asd");
					//adminControl adminFrame = new adminControl(ref1);									
					//adminFrame.setLocationRelativeTo(null);
					//adminFrame.setVisible(true);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
				}
			}
		});
		
	}

	MouseListener myMouseListener = new MouseListener() {
		public void mouseClicked(MouseEvent e) {
			//System.out.println("[MouseListener] Clicked! Button = "+((JButton) e.getSource()).getText());
		}

		public void mouseEntered(MouseEvent e) {
			//System.out.println("[MouseListener] Entered! Button = "+((JButton) e.getSource()).getText());
		}

		public void mouseExited(MouseEvent e) {
			//System.out.println("[MouseListener] Exited! Button = "+((JButton) e.getSource()).getText());
		}

		public void mousePressed(MouseEvent e) {
			//System.out.println("[MouseListener] Pressed! Button = "+((JButton) e.getSource()).getText());
		}

		public void mouseReleased(MouseEvent e) {
			if (e.getSource().equals(datesList));
			else UpdateDates();
			//checkBtn();
		}
		
	};
	
	public void UpdateFlights() {
		db.showFlightsList();				//takes selected id and uses it to show dates
		DefaultListModel<String> listModelFlights = new DefaultListModel<String>();
		for (int i = 0; i < sqlFlights.size(); i+=2) {
			listModelFlights.add(i/2, "" + sqlFlights.get(i) + " - " + sqlFlights.get(i+1));
		}	
		flightsList.setModel(listModelFlights);
	}
	
	public void UpdateDates() {		
		int index = flightsList.getSelectedIndex();	
		if (index == -1) index = 0;
		db.showDatesList(sqlFlight_id.get(index));				//takes selected id and uses it to show dates
		DefaultListModel<String> listModelFlights = new DefaultListModel<String>();
		for (int i = 0; i < sqlDates.size(); i++) {
			listModelFlights.add(i, sqlDates.get(i));
		}	
		datesList.setModel(listModelFlights);
	}
	
	public adminControl(base _ref) {
		//base ref1 = _ref;
		db = new sqliteDB(_ref);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 539);
						
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
				
		JPanel left = new JPanel();
		contentPane.add(left, BorderLayout.CENTER);
		GridBagLayout gbl_left = new GridBagLayout();
		gbl_left.columnWidths = new int[]{14, 96, 0};
		gbl_left.rowHeights = new int[]{21, 0, 29, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_left.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_left.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		left.setLayout(gbl_left);
		
		lblFlights = new JLabel("Flights");
		GridBagConstraints gbc_lblFlights = new GridBagConstraints();
		gbc_lblFlights.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlights.gridx = 0;
		gbc_lblFlights.gridy = 0;
		left.add(lblFlights, gbc_lblFlights);
		
		lblDates = new JLabel("Dates");
		GridBagConstraints gbc_lblDates = new GridBagConstraints();
		gbc_lblDates.insets = new Insets(0, 0, 5, 0);
		gbc_lblDates.gridx = 1;
		gbc_lblDates.gridy = 0;
		left.add(lblDates, gbc_lblDates);
		
		
		DefaultListModel<String> listModelFlights = new DefaultListModel<String>();
		//listModelFlights.add(0, "test space");
		
		
		flightsList = new JList<String>(listModelFlights);
		flightsList.addMouseListener(myMouseListener);
		
		GridBagConstraints gbc_flightsList = new GridBagConstraints();
		gbc_flightsList.insets = new Insets(0, 0, 0, 5);
		gbc_flightsList.fill = GridBagConstraints.BOTH;
		gbc_flightsList.gridx = 0;
		gbc_flightsList.gridy = 1;
		left.add(flightsList, gbc_flightsList);
		
		
		DefaultListModel<String> listModelDates = new DefaultListModel<String>();
		
		datesList = new JList<String>(listModelDates);
		datesList.addMouseListener(myMouseListener);
		
		GridBagConstraints gbc_datesList = new GridBagConstraints();
		gbc_datesList.fill = GridBagConstraints.BOTH;
		gbc_datesList.gridx = 1;
		gbc_datesList.gridy = 1;
		left.add(datesList, gbc_datesList);
		
		_ref.UpdateDates();
		_ref.UpdateFlights();
		
		
	}
	
	
}
