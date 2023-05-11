package gui;

import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLogic.BLFacade;
import domain.Sesion;
import domain.Usuario;

public class ReservarGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario user;

	public ReservarGUI(Usuario user) {
		this.user = user;
		initialize();
	}

	public void initialize() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<Sesion> lista = bussinessLogic.sesionesSemana();

		// Crear las columnas del JTable
		Vector<String> columns = new Vector<>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("listaActividades");

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		for (Sesion sesion : lista) {
		    Vector<Object> row = new Vector<>();
		    row.add(sesion.getFecha());
		    row.add(sesion.getPlazasDisponibles());
		    row.add(sesion.getListaActividades());
		    // ...
		    rows.add(row);
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 20, 210, 200);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		table.setBounds(0, 20, 200, 190);
		getContentPane().add(table);
		getContentPane().add(scrollPane);

		JLabel error = new JLabel();
		error.setBounds(273, 140, 197, 13);
		error.setVisible(false);
		getContentPane().add(error);

		JButton reservarBtn = new JButton("reservar");
		reservarBtn.setBounds(273, 61, 125, 38);
		reservarBtn.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Sesion sesion = lista.get(table.getSelectedRow());
				if(sesion.getPlazasDisponibles() == 0) {
					bussinessLogic.addAListaEspera(sesion, user);
					error.setText("Sesion llena asi que se añade a la lista de espera");
					error.setVisible(true);
				}
				else {
					bussinessLogic.addReserva(sesion, user);
					error.setText("Reserva completada");
					error.setVisible(true);
				}
			}

		});
		getContentPane().add(reservarBtn);

	}
}