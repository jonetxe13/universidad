package gui;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLogic.BLFacade;
import domain.Sesion;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ConsultarSesionesGUI extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ConsultarSesionesGUI() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<Sesion> lista = bussinessLogic.sesionesSemana();
		System.out.println(lista);

		// Crear las columnas del JTable
		Vector<String> columns = new Vector<>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("Sala");
		columns.add("Actividades");
		columns.add("Precio");
		// ...

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<>();
		for (Sesion sesion : lista) {
		    Vector<Object> row = new Vector<>();
		    row.add(sesion.getFecha());
		    row.add(sesion.getPlazasDisponibles());
		    row.add(sesion.getSala().getNumero());
	    	row.add(sesion.getActividad().getNombre());
	    	row.add(sesion.getPrecio());
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
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);

	}
}
