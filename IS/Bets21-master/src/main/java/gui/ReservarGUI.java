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
import domain.Sesion;
import domain.Usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;

public class ReservarGUI extends JFrame {
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
		Vector<String> columns = new Vector<String>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("listaActividades");

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
		scrollPane.setBounds(0, 20, 210, 200);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		table.setBounds(0, 20, 200, 190);
		getContentPane().add(table);
		getContentPane().add(scrollPane);
		
		JButton reservarBtn = new JButton("reservar");
		reservarBtn.setBounds(273, 61, 125, 38);
		reservarBtn.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Sesion sesion = lista.get(table.getSelectedRow());
				System.out.println("El numero de plazas disponibles es: " + sesion.getPlazasDisponibles());
				if(sesion.getPlazasDisponibles() == 0) {
					System.out.print("La sesion esta llena asi que se añade a la lista de espera");
					Sesion res = bussinessLogic.addAListaEspera(sesion, user);
					System.out.print(res.getListaEspera());
				}
				else {
					bussinessLogic.addReserva(sesion, user);					
				}
			}
			
		});
		getContentPane().add(reservarBtn);
	}
}