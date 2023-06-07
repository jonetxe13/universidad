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
		List<String> lista = bussinessLogic.createUsuario(usuario.getCorreo(), usuario.getContrasenna()).getListaReservas();
		List<Date> listaFechaSesion = new ArrayList<>();
		List<Integer> listaNumSala = new ArrayList<>();
		List<Sesion> listaSesion = new ArrayList<>();
		if(lista != null) {
			for(String s: lista) {
				String[] fechaSesionString = s.split("/");
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
				Date fecha = null;
				try {
					fecha = sdf.parse(fechaSesionString[0]);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				listaFechaSesion.add(fecha);
//				System.out.println("\nfecha " + fecha);
				listaNumSala.add(Integer.parseInt(fechaSesionString[1]));
			}
		}

		for(int i = 0; i < listaFechaSesion.size(); i++) {
//			System.out.println("la fecha es: " + listaFechaSesion.get(i));
			
			Sesion ses = bussinessLogic.getSesion(listaFechaSesion.get(i), listaNumSala.get(i));
			if(ses != null) {
				listaSesion.add(bussinessLogic.getSesion(listaFechaSesion.get(i), listaNumSala.get(i)));
			}
			else {
				System.out.println("no se encuentra la sesion");
			}
		}

		// Crear las columnas del JTable
		Vector<String> columns = new Vector<>();
		columns.add("Fecha");
		columns.add("Sala");
		columns.add("Precio");

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		Vector<Object> row = new Vector<>();
		for (Sesion sesion : listaSesion) {
			System.out.println("la sesion es: " + sesion.getFecha());
//			if(sesion != null) {
			    row.add(sesion.getFecha());
			    row.add(sesion.getSala().getNumero());
			    row.add(sesion.getPrecio());
			    rows.add(row);
//			}
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
