package presentation;


import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import businessLogic.FlightManager;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import domain.ConcreteFlight;

public class FlightBooking extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane= null;
	private JLabel lblDepartCity = new JLabel("Departing city:");
	private JLabel lblArrivalCity = new JLabel("Arrival City");
	private JLabel lblYear = new JLabel("Year:");
	private JLabel lblRoomType = new JLabel("Room Type:");
	private JLabel lblMonth = new JLabel("Month:");
	private JLabel lblDay = new JLabel("Day:");;
	private JLabel jLabelResult = new JLabel();
	private JLabel searchResult =   new JLabel();
	private JTextField departCity;
	private JTextField day = null;
	private JComboBox<String> months = null;
	private DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();

	private JTextField year = null;
	
	private JRadioButton bussinesTicket = null;
	private JRadioButton firstTicket = null;
	private JRadioButton touristTicket = null;

	private ButtonGroup fareButtonGroup = new ButtonGroup();  
	
	private JButton lookforFlights = null;
	private DefaultListModel<ConcreteFlight> flightInfo = new DefaultListModel<ConcreteFlight>();

	
	private JList<ConcreteFlight> flightList = null;
	private JButton bookFlight = null;
	
	

	
	private Collection<ConcreteFlight> concreteFlightCollection;
	
	private FlightManager businessLogic;  //  @jve:decl-index=0:
	private JScrollPane flightListScrollPane = new JScrollPane();;
	
	
	private ConcreteFlight selectedConcreteFlight;
	
	//mis atributos
	
	private static DefaultComboBoxModel elementos = new DefaultComboBoxModel();
	private static DefaultComboBoxModel elementos2 = new DefaultComboBoxModel();
	private static JComboBox comboDepart = new JComboBox(elementos);
	private final JComboBox comboArrival = new JComboBox(elementos2);

	private static DefaultComboBoxModel flights = new DefaultComboBoxModel();
	private static JComboBox comboFlightList = new JComboBox(flights);
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightBooking frame = new FlightBooking();
					frame.setBusinessLogic(new FlightManager());
					frame.setVisible(true);
					frame.annadirInfo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Custom operations
	public void setBusinessLogic(FlightManager g) {businessLogic = g;}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.setLenient(false); // To avoid use heuristics to parse inputs as dates
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}
	
	private void annadirInfo() {
		for (String departCityCombo: businessLogic.getAllDepartingCities())
			elementos.addElement(departCityCombo);
		
		comboDepart.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					for (String arrivalCityCombo: businessLogic.getArrivalCitiesFrom(comboDepart.getSelectedItem().toString()))
						elementos2.addElement(arrivalCityCombo);
				}
			}
		});
			
		comboDepart.setSelectedIndex(1);
		comboArrival.setSelectedIndex(1);
	}

	/**
	 * Create the frame
	 * 
	 * @return void
	 */
	private  FlightBooking() {
		
		setTitle("Book Flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDepartCity = new JLabel("Depart City");
		lblDepartCity.setBounds(21, 11, 103, 16);
		contentPane.add(lblDepartCity);
		
		
		lblYear = new JLabel("Year:");
		lblYear.setBounds(21, 62, 33, 16);
		contentPane.add(lblYear);
		
		lblMonth = new JLabel("Month:");
		lblMonth.setBounds(117, 62, 50, 16);
		contentPane.add(lblMonth);
	    
		months = new JComboBox<String>();
		months.setBounds(163, 58, 116, 27);
		contentPane.add(months);
		months.setModel(monthNames);
		
		monthNames.addElement("January");
		monthNames.addElement("February");
		monthNames.addElement("March");
		monthNames.addElement("April");
		monthNames.addElement("May");
		monthNames.addElement("June");
		monthNames.addElement("July");
		monthNames.addElement("August");
		monthNames.addElement("September");
		monthNames.addElement("October");
		monthNames.addElement("November");
		monthNames.addElement("December");
		months.setSelectedIndex(1);
		
		lblDay = new JLabel("Day:");
		lblDay.setBounds(291, 62, 38, 16);
		contentPane.add(lblDay);
		
		day = new JTextField();
		day.setText("23");
		day.setBounds(331, 57, 50, 26);
		contentPane.add(day);
		day.setColumns(10);
		
		lblRoomType = new JLabel("Seat Type:");
		lblRoomType.setBounds(21, 242, 84, 16);
		contentPane.add(lblRoomType);
		
		
		bussinesTicket = new JRadioButton("Business");
		bussinesTicket.setSelected(false);
		fareButtonGroup.add(bussinesTicket);
		bussinesTicket.setBounds(99, 238, 101, 23);
		contentPane.add(bussinesTicket);
		
		firstTicket = new JRadioButton("First");
		fareButtonGroup.add(firstTicket);
		firstTicket.setBounds(202, 238, 77, 23);
		contentPane.add(firstTicket);
		
		touristTicket = new JRadioButton("Tourist");
		fareButtonGroup.add(touristTicket);
		touristTicket.setBounds(278, 238, 77, 23);
		contentPane.add(touristTicket);
				
		lookforFlights = new JButton("Look for Concrete Flights");
		lookforFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookFlight.setEnabled(false);
				flightInfo.clear();
				bookFlight.setText("");
				
				java.util.Date date =newDate(Integer.parseInt(year.getText()),months.getSelectedIndex(),Integer.parseInt(day.getText()));
				 
				concreteFlightCollection=businessLogic.getConcreteFlights((String) comboDepart.getSelectedItem(),comboArrival.getSelectedItem().toString(),date);
				for (ConcreteFlight f : concreteFlightCollection) 
					flights.addElement(f); 
				if (concreteFlightCollection.isEmpty()) searchResult.setText("No flights in that city in that date");
				else searchResult.setText("Choose an available flight in this list:");
			}
		});
		
		lookforFlights.setBounds(81, 90, 261, 40);
		contentPane.add(lookforFlights);	
		
		jLabelResult = new JLabel("");
		jLabelResult.setBounds(109, 180, 243, 16);
		contentPane.add(jLabelResult);
		
		comboFlightList.setBounds(81, 169, 280, 21);
		contentPane.add(comboFlightList);
		
		comboFlightList.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
//				bookFlight.setEnabled(false);
													 
				selectedConcreteFlight = (ConcreteFlight) comboFlightList.getSelectedItem();
				//desactivar los botones si el valor del vuelo es 0
				if(selectedConcreteFlight.getBusinessNumber() == 0)
					bussinesTicket.setEnabled(false);
				if(selectedConcreteFlight.getTouristNumber() == 0)
					touristTicket.setEnabled(false);
				if(selectedConcreteFlight.getFirstNumber() == 0)
					firstTicket.setEnabled(false);
				bookFlight.setText("Book: "+selectedConcreteFlight);  // TODO Auto-generated Event stub valueChanged()
			}
		});


		/////
//		flightList = new JList<ConcreteFlight>();
//		flightList.setModel(flightInfo);
//		flightList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
//
//			public void valueChanged(javax.swing.event.ListSelectionEvent e) {
////				bookFlight.setEnabled(false);
//				if (e.getValueIsAdjusting()) return; // The event is activated twice: Before the value is changed, and after changed 
//													 // We need to act only after changed 
//				if (!flightList.isSelectionEmpty()){  
//													 
//					selectedConcreteFlight = (ConcreteFlight) flightList.getSelectedValue();
//					//desactivar los botones si el valor del vuelo es 0
//					if(selectedConcreteFlight.getBusinessNumber() == 0)
//						bussinesTicket.setEnabled(false);
//					if(selectedConcreteFlight.getTouristNumber() == 0)
//						touristTicket.setEnabled(false);
//					if(selectedConcreteFlight.getFirstNumber() == 0)
//						firstTicket.setEnabled(false);
//					bookFlight.setText("Book: "+selectedConcreteFlight);  // TODO Auto-generated Event stub valueChanged()
//				}
//			}
//		});
//		
//		flightListScrollPane.setBounds(new Rectangle(64, 159, 336, 71));
//		flightListScrollPane.setViewportView(flightList);
//		contentPane.add(flightListScrollPane);
		///////
		//hacer que los botones activen el boton de reserva
		bussinesTicket.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bookFlight.setEnabled(bussinesTicket.isSelected());
			}
		});
		firstTicket.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bookFlight.setEnabled(firstTicket.isSelected());
			}
		});
		touristTicket.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bookFlight.setEnabled(touristTicket.isSelected());
			}
		});
		
		bookFlight = new JButton("");
		bookFlight.setEnabled(false);
		bookFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=0;
				boolean error=false;
				if (bussinesTicket.isSelected()) { 
					num=selectedConcreteFlight.getBusinessNumber();
					if (num>0) selectedConcreteFlight.setBusinessNumber(num-1); else error=true; 
				}
				else if (firstTicket.isSelected()) {
					num=selectedConcreteFlight.getFirstNumber();
					if (num>0) selectedConcreteFlight.setFirstNumber(num-1); else error=true;
				}
				else if (touristTicket.isSelected()) {
					num=selectedConcreteFlight.getTouristNumber();
					if (num>0) selectedConcreteFlight.setTouristNumber(num-1); else error=true;
				}
				
				if (error) bookFlight.setText("Error: There were no seats available!");
				else bookFlight.setText("Booked. #seat left: "+(num-1));
			}
		});
		
		bookFlight.setBounds(31, 273, 399, 40);
		contentPane.add(bookFlight);

		year = new JTextField();
		year.setText("2023");
		year.setBounds(57, 57, 50, 26);
		contentPane.add(year);
		year.setColumns(10);
		
		lblArrivalCity.setBounds(21, 39, 84, 16);
		contentPane.add(lblArrivalCity);
		
		searchResult.setBounds(57, 130, 314, 16);
		contentPane.add(searchResult);
		comboDepart.setBounds(99, 9, 243, 21);
		
		contentPane.add(comboDepart);
		comboArrival.setBounds(99, 31, 243, 21);
		
		contentPane.add(comboArrival);
		

	}
}  //  @jve:decl-index=0:visual-constraint="18,9"
