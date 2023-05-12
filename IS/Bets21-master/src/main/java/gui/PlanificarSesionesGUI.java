package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Actividad;
import domain.Encargado;
import domain.Sesion;

public class PlanificarSesionesGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private Encargado encargado;
	private JTextField fechaTextField;
	private JTextField salaTextField;
	private JTextField listaActividadesTextField;
	private JTextField precioTextField;
	private JTextField plazasTextField;
	private List<Sesion> lista;

	public PlanificarSesionesGUI(Encargado encargado) {
		this.encargado = encargado;
		initialize();
	}

	public void initialize() {
		if(encargado == null) {
			System.out.println("el encargado no existe");
			System.exit(PROPERTIES);
		}
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		lista = bussinessLogic.sesionesSemana();
		System.out.println(lista);

		// Crear las columnas del JTable
		Vector<String> columns = new Vector<>();
		columns.add("Fecha");
		columns.add("Sala");
		columns.add("precio");
		columns.add("Plazas");
		columns.add("listaActividades");
		// ...

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		for (Sesion sesion : lista) {
		    Vector<Object> row = new Vector<>();
		    row.add(sesion.getFecha());
		    row.add(sesion.getSala().getNumero());
		    row.add(sesion.getPrecio());
		    row.add(sesion.getPlazasDisponibles());

		    List<String> nomAct = new ArrayList<>();
		    List<Actividad> listAct = sesion.getListaActividades();
		    if(listAct != null) {
		    	for(Actividad act: listAct) {
		    		nomAct.add(act.getNombre());
		    	}
		    }
	    	row.add(nomAct);
		    // ...
		    rows.add(row);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 8, 480, 450);
		getContentPane().add(scrollPane);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		scrollPane.setColumnHeaderView(table);

		fechaTextField = new JTextField();
		fechaTextField.setBounds(510, 23, 96, 19);
		getContentPane().add(fechaTextField);
		fechaTextField.setColumns(10);

		salaTextField = new JTextField();
		salaTextField.setBounds(538, 52, 47, 19);
		getContentPane().add(salaTextField);
		salaTextField.setColumns(10);

		listaActividadesTextField = new JTextField();
		listaActividadesTextField.setBounds(500, 142, 126, 19);
		getContentPane().add(listaActividadesTextField);
		listaActividadesTextField.setColumns(10);

		precioTextField = new JTextField();
		precioTextField.setBounds(500, 171, 58, 19);
		getContentPane().add(precioTextField);
		precioTextField.setColumns(10);

		plazasTextField = new JTextField();
		plazasTextField.setBounds(568, 171, 58, 19);
		getContentPane().add(plazasTextField);
		plazasTextField.setColumns(10);

		JLabel error = new JLabel();
		error.setBounds(500, 200, 197, 13);
		error.setVisible(false);
		getContentPane().add(error);
		error.setText("se ha añadido correctamente");
		error.setVisible(true);


		JButton annadirSesionbtn= new JButton("Annadir sesion");
		annadirSesionbtn.setBounds(500, 197, 126, 38);
		annadirSesionbtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.out.println(fechaTextField.getText() + " " + salaTextField.getText() + " " + listaActividadesTextField.getText() + " " + precioTextField.getText() + " " + plazasTextField.getText());
				bussinessLogic.annadirSesion(fechaTextField.getText(), salaTextField.getText(), listaActividadesTextField.getText(), precioTextField.getText(), plazasTextField.getText());
				error.setText("se ha añadido correctamente");
				error.setVisible(true);
			}
		});
		getContentPane().add(annadirSesionbtn);

		JButton quitarSesionbtn = new JButton("Quitar sesion");
		quitarSesionbtn.setBounds(500, 81, 126, 38);
		quitarSesionbtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					Sesion sesion = lista.get(table.getSelectedRow());
					bussinessLogic.quitarSesion(sesion.getFecha(), Integer.toString(sesion.getSala().getNumero()));
				}
				else {
					bussinessLogic.quitarSesion(fechaTextField.getText(), salaTextField.getText());
				}
				error.setText("se ha quitado correctamente");
				error.setVisible(true);
			}
		});
		getContentPane().add(quitarSesionbtn);

		JLabel lblNewLabel = new JLabel("fecha (YYYY-MM-dd)");
		lblNewLabel.setBounds(510, 10, 96, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("num sala");
		lblNewLabel_1.setBounds(544, 40, 57, 13);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("lista actividades (separadas con comas)");
		lblNewLabel_2.setBounds(500, 129, 193, 13);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("precio");
		lblNewLabel_3.setBounds(500, 160, 45, 13);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("plazas");
		lblNewLabel_4.setBounds(568, 160, 45, 13);
		getContentPane().add(lblNewLabel_4);
	}
}