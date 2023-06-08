package gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import businessLogic.BLFacade;
import domain.Cargo;
import domain.Sesion;
import domain.Usuario;

public class PagarFacturasGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	/**
	 * Create the application.
	 */
	public PagarFacturasGUI(Usuario user) {
		this.usuario = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setLayout(null);
		
		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		System.out.println("el usuario: " +usuario.getCorreo());
//		List<Usuario> listaUsuario = bussinessLogic.getListaUserCargos(this.usuario);
		List<Sesion> listaSesion = bussinessLogic.getListaSesionCargos(this.usuario);
		

		// Crear las columnas del JTable
		Vector<String> columns = new Vector<>();
		columns.add("Usuario");
		columns.add("Sesion");
		columns.add("Precio");

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		for (int i = 0; i < listaSesion.size(); i++) {
			Vector<Object> row = new Vector<>();
			    row.add(usuario.getCorreo());
			    row.add(listaSesion.get(i).getFecha());
			    row.add(listaSesion.get(i).getPrecio());
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
	}

}
