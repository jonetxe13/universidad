package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import businessLogic.BLFacade;
import domain.Sesion;
import domain.Usuario;

public class CancelarReservaGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private Usuario user;

	public CancelarReservaGUI(Usuario user) {
		this.user = user;
		initialize();
	}

	public void initialize() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<String> lista = bussinessLogic.createUsuario(user.getCorreo(), user.getContrasenna()).getListaReservas();
//		System.out.println("la lista de las reservas" + lista);
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
		columns.add("Plazas");
		columns.add("Sala");
		columns.add("listaActividades");

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		for (Sesion sesion : listaSesion) {
			Vector<Object> row = new Vector<>();
		    row.add(sesion.getFecha());
		    row.add(sesion.getPlazasDisponibles());
		    row.add(sesion.getSala().getNumero());
		    row.add(sesion.getActividad());
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

		JLabel error = new JLabel();
		error.setBounds(273, 140, 197, 13);
		error.setVisible(false);
		getContentPane().add(error);

		JButton cancelarReservaBtn = new JButton("Cancelar reserva");
		cancelarReservaBtn.setBounds(519, 140, 125, 38);
		cancelarReservaBtn.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					Sesion sesion = listaSesion.get(table.getSelectedRow());
					bussinessLogic.cancelarReserva(sesion, user);
//					getContentPane().add(table);
					error.setText("Reserva cancelada");
					error.setVisible(true);
				}
				else {
					error.setText("No tienes nada seleccionado");
					error.setVisible(true);
				}
			}
		});
		getContentPane().add(cancelarReservaBtn);
	}
}