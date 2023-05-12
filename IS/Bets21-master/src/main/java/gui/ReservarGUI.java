package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLogic.BLFacade;
import domain.Actividad;
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
		    List<String> listAct = new ArrayList<String>();
		    if(sesion.getListaActividades() != null) {
		    for(Actividad act: sesion.getListaActividades() ) {
		    	listAct.add(act.getNombre());
		    }
	    	row.add(listAct);
		    	
		    }
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
				Sesion sesion = null;
				if(table.getSelectedRow() != -1) {
					System.out.println("hasta aqui llega 1");
					sesion = lista.get(table.getSelectedRow());
					boolean annadido = bussinessLogic.addReserva(sesion, user);
					System.out.println("hasta aqui llega 2");
					if(!annadido) {
						System.out.println("hasta aqui llega 3");
						error.setText("Sesion llena asi que se añade a la lista de espera");
						error.setVisible(true);
					}
					else {
						table.updateUI();
						error.setText("Reserva completada");
						error.setVisible(true);
					}
				}
				else {
					error.setText("Selecciona una sesion de la tabla");
					error.setVisible(true);
				}
			}

		});
		getContentPane().add(reservarBtn);

	}
}