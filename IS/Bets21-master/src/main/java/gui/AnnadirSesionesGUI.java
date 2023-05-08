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

public class AnnadirSesionesGUI extends JFrame {
	private Encargado encargado;
	private JTextField fechaTextField;
	private JTextField salaTextField;
	private JTextField listaActividadesTextField;
	private JTextField precioTextField;
	private JTextField plazasTextField;
	
	public AnnadirSesionesGUI(Encargado encargado) {
		this.encargado = encargado;
		initialize();
	}
	
	public void initialize() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<Sesion> lista = bussinessLogic.sesionesSemana();
		System.out.println(lista);
		
		// Crear las columnas del JTable
		Vector<String> columns = new Vector<String>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("listaActividades");
		columns.add("Sala");
		// ...

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
		for (Sesion sesion : lista) {
		    Vector<Object> row = new Vector<Object>();
		    row.add(sesion.getFecha());
		    row.add(sesion.getSala());
		    row.add(sesion.getPlazasDisponibles());
		    row.add(sesion.getListaActividades());
		    // ...
		    rows.add(row);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 20, 290, 220);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		table.setBounds(5, 25, 260, 210);
		getContentPane().add(table);
		getContentPane().add(scrollPane);
		
		fechaTextField = new JTextField();
		fechaTextField.setBounds(320, 23, 96, 19);
		getContentPane().add(fechaTextField);
		fechaTextField.setColumns(10);
		
		salaTextField = new JTextField();
		salaTextField.setBounds(320, 52, 47, 19);
		getContentPane().add(salaTextField);
		salaTextField.setColumns(10);
		
		listaActividadesTextField = new JTextField();
		listaActividadesTextField.setBounds(320, 81, 96, 19);
		getContentPane().add(listaActividadesTextField);
		listaActividadesTextField.setColumns(10);
		
		precioTextField = new JTextField();
		precioTextField.setBounds(320, 115, 96, 19);
		getContentPane().add(precioTextField);
		precioTextField.setColumns(10);
		
		plazasTextField = new JTextField();
		plazasTextField.setBounds(374, 52, 42, 19);
		getContentPane().add(plazasTextField);
		plazasTextField.setColumns(10);
		
		JButton annadirSesionbtn= new JButton("Annadir sesion");
		annadirSesionbtn.setBounds(304, 158, 125, 38);
		annadirSesionbtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
				bussinessLogic.annadirSesion(fechaTextField.getText(), salaTextField.getText(), listaActividadesTextField.getText(), precioTextField.getText(), plazasTextField.getText());
			}
		});
		getContentPane().add(annadirSesionbtn);
	}
}