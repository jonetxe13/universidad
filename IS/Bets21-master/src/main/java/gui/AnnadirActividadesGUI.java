package gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import businessLogic.BLFacade;
import domain.Actividad;
import domain.Encargado;
import domain.Sesion;
import domain.Usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

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
		Vector<String> columns = new Vector<String>();
		columns.add("Nombre");
		columns.add("Nivel");
		columns.add("Precio");
		// ...

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
		for (Actividad act : lista) {
		    Vector<Object> row = new Vector<Object>();
		    row.add(act.getNombre());
		    row.add(act.getGradoExigencia());
		    row.add(act.getPrecio());
		    
		    // ...
		    rows.add(row);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 8, 480, 450);
		getContentPane().add(scrollPane);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		scrollPane.setColumnHeaderView(table);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(510, 23, 96, 19);
		getContentPane().add(nombreTextField);
		nombreTextField.setColumns(10);
		
		nivelTextField = new JTextField();
		nivelTextField.setBounds(538, 52, 47, 19);
		getContentPane().add(nivelTextField);
		nivelTextField.setColumns(10);
		
		precioTextField = new JTextField();
		precioTextField.setBounds(500, 171, 58, 19);
		getContentPane().add(precioTextField);
		precioTextField.setColumns(10);
		
		
		JButton annadirActividadbtn= new JButton("Annadir sesion");
		annadirActividadbtn.setBounds(500, 197, 126, 38);
		annadirActividadbtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
				bussinessLogic.annadirActividad(nombreTextField.getText(), nivelTextField.getText(), precioTextField.getText());
			}
		});
		getContentPane().add(annadirActividadbtn);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(510, 10, 96, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nivel");
		lblNewLabel_1.setBounds(544, 40, 57, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("precio");
		lblNewLabel_3.setBounds(500, 160, 45, 13);
		getContentPane().add(lblNewLabel_3);
	}
}