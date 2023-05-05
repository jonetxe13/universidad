package gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListModel;

import businessLogic.BLFacade;
import domain.Sesion;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

public class ConsultarSesionesGUI extends JFrame {
	public ConsultarSesionesGUI() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<Sesion> lista = bussinessLogic.sesionesSemana();
		System.out.println(lista);
		
		// Crear las columnas del JTable
		Vector<String> columns = new Vector<String>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("listaActividades");
		// ...

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
		for (Sesion sesion : lista) {
		    Vector<Object> row = new Vector<Object>();
		    row.add(sesion.getFecha());
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
		
	}

}
