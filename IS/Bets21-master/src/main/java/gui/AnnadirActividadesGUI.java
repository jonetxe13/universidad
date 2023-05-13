package gui;

import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import businessLogic.BLFacade;
import domain.Actividad;

public class AnnadirActividadesGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextField nombreTextField;
	private JTextField nivelTextField;
	private JTextField precioTextField;

	public AnnadirActividadesGUI() {
		initialize();
	}

	public void initialize() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<Actividad> lista = bussinessLogic.getActividades();

		// Crear las columnas del JTable
		Vector<String> columns = new Vector<>();
		columns.add("Nombre");
		columns.add("Nivel");
		columns.add("Precio");
		// ...

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		for (Actividad act : lista) {
		    Vector<Object> row = new Vector<>();
		    row.add(act.getNombre());
		    row.add(act.getGradoExigencia());
		    row.add(act.getPrecio());

		    // ...
		    rows.add(row);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 493, 333);
		getContentPane().add(scrollPane);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(189);
		scrollPane.setViewportView(table);

		nombreTextField = new JTextField();
		nombreTextField.setBounds(504, 30, 96, 19);
		getContentPane().add(nombreTextField);
		nombreTextField.setColumns(10);

		nivelTextField = new JTextField();
		nivelTextField.setBounds(525, 64, 47, 19);
		getContentPane().add(nivelTextField);
		nivelTextField.setColumns(10);

		precioTextField = new JTextField();
		precioTextField.setBounds(520, 93, 58, 19);
		getContentPane().add(precioTextField);
		precioTextField.setColumns(10);

		JLabel error = new JLabel();
		error.setBounds(500, 200, 197, 13);
		error.setVisible(false);
		getContentPane().add(error);

		JButton annadirActividadbtn= new JButton("Annadir actividad");
		annadirActividadbtn.setBounds(497, 122, 106, 39);
		annadirActividadbtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
				if(nombreTextField.getText().isEmpty() && nivelTextField.getText().isEmpty() && precioTextField.getText().isEmpty()) {
					error.setText("introduce los datos");
					error.setVisible(true);
				}
				else {
					bussinessLogic.annadirActividad(nombreTextField.getText(), nivelTextField.getText(), precioTextField.getText());
					error.setText("se ha añadido correctamente");
					error.setVisible(true);
				}
			}
		});
		getContentPane().add(annadirActividadbtn);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(525, 14, 47, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nivel");
		lblNewLabel_1.setBounds(534, 50, 57, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Precio");
		lblNewLabel_3.setBounds(533, 81, 45, 13);
		getContentPane().add(lblNewLabel_3);
	}
}