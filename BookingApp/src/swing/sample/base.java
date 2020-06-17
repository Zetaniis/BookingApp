package swing.sample;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;  			//log4j
import java.sql.*;							//sql

import java.lang.String;

import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.Component;

public class base extends JFrame {

	private JPanel contentPane;
	private JTextField surnameField;
	private JTextField nameField;
	private JButton btnBookIt;
	private JLabel lblMail;
	private JTextField mailField;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNew;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JLabel lblNumOfTickets;
	private JSeparator separator;
	private JMenuItem mntmQuit;
	private JSpinner numOfTicketsSpinner;
	private JPanel middle;
	private JList datesList;
	private JList flightsList;
	private JLabel lblFlights;
	private JLabel lblDates;
	private JPanel right;
	private JButton btnAddFlight;
	private JButton btnAddDate;
	private JButton btnChangeDate;
	private JButton btnDeleteDate;
	private JButton btnDeleteFlight;
	private JTextField dateField;
	private JTextField sourceField;
	private JCheckBox chckbxNewsletter;
	private JLabel lblSubscribeToNewsletter;
	private JSpinner spinner_3;
	private JSpinner spinner_4;
	private JPanel panel_4;
	private JPanel panel_3;
	private JSpinner spinner_1;
	private JSpinner spinner_2;
	private JLabel lblSecondName;
	private JTextField secNameField;
	
	private String name = "";
	private String secondName = "";
	private String surname = "";
	private String mail = "";
	private int ticketsNum = 0;
	private boolean subscribe = false;
	private String newFlight = "";
	private String newDate = ""; 			//to be changed
	
	private String sqlFlights = "";
	private String sqlDates = "";
	
	final static Logger log = Logger.getLogger(base.class.getName());				//log4j
	private JButton btnNewButton;
	private JTextField destinationField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					base frame = new base();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 	

	/**
	 * Create the frame.
	 */
	public base() {
		
		
		log.debug("testing");
		 
		ActionListener myActionListener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("[ActionListener] Button = "+e.getActionCommand());
    			
    			switch(e.getActionCommand())
    			{
       				case "Book it":
           			System.out.println("[ActionListener] Button = "+e.getActionCommand());
           			break;
           			case "Delete flight":
           			System.out.println("[ActionListener] Button = "+e.getActionCommand());
           			break;
           			case "Add delay":
           			System.out.println("[ActionListener] Button = "+e.getActionCommand());
           			break;
           			case "Delete date":
           			System.out.println("[ActionListener] Button = "+e.getActionCommand());
           			break;
           			case "Add Date":
           			System.out.println("[ActionListener] Button = "+e.getActionCommand());
           			break;
           			case "Add Flight":
           			System.out.println("[ActionListener] Button = "+e.getActionCommand());
           			break;
           			
    			}
    		}    		
    	};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 539);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		mntmNew = new JMenuItem("New");
		mnNewMenu.add(mntmNew);
		
		mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);
		
		mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);
		
		separator = new JSeparator();
		mnNewMenu.add(separator);
		
		mntmQuit = new JMenuItem("Quit");
		mnNewMenu.add(mntmQuit);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//contentPane.addWindowListener(myWindowListener);
		//contentPane.addWindowStateListener(myWindowStateListener);
		
		JPanel left = new JPanel();
		contentPane.add(left, BorderLayout.WEST);
		GridBagLayout gbl_left = new GridBagLayout();
		gbl_left.columnWidths = new int[]{104, 96, 0};
		gbl_left.rowHeights = new int[]{21, 0, 29, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_left.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_left.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		left.setLayout(gbl_left);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		left.add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		//nameField.getText();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.anchor = GridBagConstraints.WEST;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		left.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		lblSecondName = new JLabel("Second name");
		GridBagConstraints gbc_lblSecondName = new GridBagConstraints();
		gbc_lblSecondName.anchor = GridBagConstraints.EAST;
		gbc_lblSecondName.insets = new Insets(0, 0, 5, 5);
		gbc_lblSecondName.gridx = 0;
		gbc_lblSecondName.gridy = 1;
		left.add(lblSecondName, gbc_lblSecondName);
		
		secNameField = new JTextField();
		secNameField.setColumns(10);
		GridBagConstraints gbc_secNameField = new GridBagConstraints();
		gbc_secNameField.insets = new Insets(0, 0, 5, 0);
		gbc_secNameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_secNameField.gridx = 1;
		gbc_secNameField.gridy = 1;
		left.add(secNameField, gbc_secNameField);
		
		JLabel lblSurname = new JLabel("Surname");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 0;
		gbc_lblSurname.gridy = 2;
		left.add(lblSurname, gbc_lblSurname);
		
		surnameField = new JTextField();
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.anchor = GridBagConstraints.WEST;
		gbc_surnameField.insets = new Insets(0, 0, 5, 0);
		gbc_surnameField.gridx = 1;
		gbc_surnameField.gridy = 2;
		left.add(surnameField, gbc_surnameField);
		surnameField.setColumns(10);
		
		lblNumOfTickets = new JLabel("Num of tickets");
		GridBagConstraints gbc_lblNumOfTickets = new GridBagConstraints();
		gbc_lblNumOfTickets.anchor = GridBagConstraints.EAST;
		gbc_lblNumOfTickets.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumOfTickets.gridx = 0;
		gbc_lblNumOfTickets.gridy = 3;
		left.add(lblNumOfTickets, gbc_lblNumOfTickets);
		
		numOfTicketsSpinner = new JSpinner();
		SpinnerModel modeltau = new SpinnerNumberModel(0, 0, 99999, 1); 			//Integer.MAX_VALUE
		numOfTicketsSpinner.setModel(modeltau);
		GridBagConstraints gbc_numOfTicketsSpinner = new GridBagConstraints();
		gbc_numOfTicketsSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_numOfTicketsSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_numOfTicketsSpinner.gridx = 1;
		gbc_numOfTicketsSpinner.gridy = 3;
		left.add(numOfTicketsSpinner, gbc_numOfTicketsSpinner);
		
		lblMail = new JLabel("Mail");
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 0;
		gbc_lblMail.gridy = 7;
		left.add(lblMail, gbc_lblMail);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		GridBagConstraints gbc_mailField = new GridBagConstraints();
		gbc_mailField.insets = new Insets(0, 0, 5, 0);
		gbc_mailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailField.gridx = 1;
		gbc_mailField.gridy = 7;
		left.add(mailField, gbc_mailField);
		
		lblSubscribeToNewsletter = new JLabel("Subscribe to newsletter");
		GridBagConstraints gbc_lblSubscribeToNewsletter = new GridBagConstraints();
		gbc_lblSubscribeToNewsletter.anchor = GridBagConstraints.WEST;
		gbc_lblSubscribeToNewsletter.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubscribeToNewsletter.gridx = 0;
		gbc_lblSubscribeToNewsletter.gridy = 8;
		left.add(lblSubscribeToNewsletter, gbc_lblSubscribeToNewsletter);
		
		chckbxNewsletter = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewsletter = new GridBagConstraints();
		gbc_chckbxNewsletter.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewsletter.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewsletter.gridx = 1;
		gbc_chckbxNewsletter.gridy = 8;
		left.add(chckbxNewsletter, gbc_chckbxNewsletter);
		
		btnBookIt = new JButton("Book it");
		GridBagConstraints gbc_btnBookIt = new GridBagConstraints();
		gbc_btnBookIt.insets = new Insets(0, 0, 5, 5);
		gbc_btnBookIt.gridx = 0;
		gbc_btnBookIt.gridy = 9;
		left.add(btnBookIt, gbc_btnBookIt);
		btnBookIt.addActionListener(myActionListener);
		
		middle = new JPanel();
		contentPane.add(middle, BorderLayout.CENTER);
		GridBagLayout gbl_middle = new GridBagLayout();
		gbl_middle.columnWidths = new int[]{142, 122, 0};
		gbl_middle.rowHeights = new int[]{0, 0, 0};
		gbl_middle.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_middle.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		middle.setLayout(gbl_middle);
		
		lblFlights = new JLabel("Flights");
		GridBagConstraints gbc_lblFlights = new GridBagConstraints();
		gbc_lblFlights.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlights.gridx = 0;
		gbc_lblFlights.gridy = 0;
		middle.add(lblFlights, gbc_lblFlights);
		
		lblDates = new JLabel("Dates");
		GridBagConstraints gbc_lblDates = new GridBagConstraints();
		gbc_lblDates.insets = new Insets(0, 0, 5, 0);
		gbc_lblDates.gridx = 1;
		gbc_lblDates.gridy = 0;
		middle.add(lblDates, gbc_lblDates);
		
		
		DefaultListModel listModelFlights = new DefaultListModel();
		listModelFlights.add(0, "test space");
		listModelFlights.add(0, "test space2");
		listModelFlights.add(0, "test space3");
		listModelFlights.add(0, "test space4");
		
		flightsList = new JList(listModelFlights);
		GridBagConstraints gbc_flightsList = new GridBagConstraints();
		gbc_flightsList.insets = new Insets(0, 0, 0, 5);
		gbc_flightsList.fill = GridBagConstraints.BOTH;
		gbc_flightsList.gridx = 0;
		gbc_flightsList.gridy = 1;
		middle.add(flightsList, gbc_flightsList);
		
		
		DefaultListModel listModelDates = new DefaultListModel();
		listModelDates.add(0, "test space");
		listModelDates.add(0, "test space2");
		listModelDates.add(0, "test space3");
		listModelDates.add(0, "test space4");
		
		datesList = new JList(listModelDates);
		GridBagConstraints gbc_datesList = new GridBagConstraints();
		gbc_datesList.fill = GridBagConstraints.BOTH;
		gbc_datesList.gridx = 1;
		gbc_datesList.gridy = 1;
		middle.add(datesList, gbc_datesList);
		
		right = new JPanel();
		contentPane.add(right, BorderLayout.EAST);
		GridBagLayout gbl_right = new GridBagLayout();
		gbl_right.columnWidths = new int[]{0, 0, 0};
		gbl_right.rowHeights = new int[]{0, 0, 26, 35, 0, 0, 0, 0, 0};
		gbl_right.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_right.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		right.setLayout(gbl_right);
		
		sourceField = new JTextField();
		GridBagConstraints gbc_sourceField = new GridBagConstraints();
		gbc_sourceField.insets = new Insets(0, 0, 5, 5);
		gbc_sourceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_sourceField.gridx = 0;
		gbc_sourceField.gridy = 0;
		right.add(sourceField, gbc_sourceField);
		sourceField.setColumns(10);
		
		btnAddFlight = new JButton("Add Source");
		GridBagConstraints gbc_btnAddFlight = new GridBagConstraints();
		gbc_btnAddFlight.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddFlight.gridx = 1;
		gbc_btnAddFlight.gridy = 0;
		right.add(btnAddFlight, gbc_btnAddFlight);
		btnAddFlight.addActionListener(myActionListener);
		
		destinationField = new JTextField();
		destinationField.setColumns(10);
		GridBagConstraints gbc_destinationField = new GridBagConstraints();
		gbc_destinationField.insets = new Insets(0, 0, 5, 5);
		gbc_destinationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationField.gridx = 0;
		gbc_destinationField.gridy = 1;
		right.add(destinationField, gbc_destinationField);
		
		btnNewButton = new JButton("Add Destination");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		right.add(btnNewButton, gbc_btnNewButton);
		
		dateField = new JTextField();
		GridBagConstraints gbc_dateField = new GridBagConstraints();
		gbc_dateField.insets = new Insets(0, 0, 5, 5);
		gbc_dateField.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateField.gridx = 0;
		gbc_dateField.gridy = 2;
		right.add(dateField, gbc_dateField);
		dateField.setColumns(10);
		
		btnAddDate = new JButton("Add Date");
		GridBagConstraints gbc_btnAddDate = new GridBagConstraints();
		gbc_btnAddDate.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddDate.gridx = 1;
		gbc_btnAddDate.gridy = 2;
		right.add(btnAddDate, gbc_btnAddDate);
		btnAddDate.addActionListener(myActionListener);
		
		panel_3 = new JPanel();	
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		right.add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		spinner_1 = new JSpinner();
		panel_3.add(spinner_1);
		
		spinner_2 = new JSpinner();
		panel_3.add(spinner_2);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 3;
		right.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		spinner_3 = new JSpinner();
		panel_4.add(spinner_3);
		
		spinner_4 = new JSpinner();
		panel_4.add(spinner_4);
		
		btnChangeDate = new JButton("Change date");
		GridBagConstraints gbc_btnChangeDate = new GridBagConstraints();
		gbc_btnChangeDate.insets = new Insets(0, 0, 5, 5);
		gbc_btnChangeDate.gridx = 0;
		gbc_btnChangeDate.gridy = 4;
		right.add(btnChangeDate, gbc_btnChangeDate);
		btnChangeDate.addActionListener(myActionListener);
		
		btnDeleteFlight = new JButton("Delete flight");
		GridBagConstraints gbc_btnDeleteFlight = new GridBagConstraints();
		gbc_btnDeleteFlight.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteFlight.gridx = 0;
		gbc_btnDeleteFlight.gridy = 5;
		right.add(btnDeleteFlight, gbc_btnDeleteFlight);
		btnDeleteFlight.addActionListener(myActionListener);
		
		btnDeleteDate = new JButton("Delete date");
		GridBagConstraints gbc_btnDeleteDate = new GridBagConstraints();
		gbc_btnDeleteDate.insets = new Insets(0, 0, 5, 0);
		gbc_btnDeleteDate.gridx = 1;
		gbc_btnDeleteDate.gridy = 5;
		right.add(btnDeleteDate, gbc_btnDeleteDate);
		btnDeleteDate.addActionListener(myActionListener);
	}
}
