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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;

public class CancelarReservaGUI extends JFrame {
	private Usuario user;
	
	public CancelarReservaGUI(Usuario user) {
		this.user = user;
		initialize();
	}
	
	public void initialize() {
		getContentPane().setLayout(null);

		BLFacade bussinessLogic = RegistroGUI.getBusinessLogic();
		List<String> lista = bussinessLogic.createUsuario(user.getCorreo(), user.getContrasenna()).getListaReservas();
		List<String> listaNomSesion = new ArrayList<String>();
		List<Sesion> listaSesion = new ArrayList<Sesion>();
		
		for(String s: lista) {
			String[] nomSesion = s.split("/");
			listaNomSesion.add(nomSesion[0]);
		}
		
		for(String s: listaNomSesion) {
			listaSesion.add(bussinessLogic.getSesion(s));
		}
		
		// Crear las columnas del JTable
		Vector<String> columns = new Vector<String>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("listaActividades");

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
		for (Sesion sesion : listaSesion) {
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
		
		JButton cancelarReservaBtn = new JButton("Cancelar reserva");
		cancelarReservaBtn.setBounds(273, 61, 125, 38);
		cancelarReservaBtn.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					Sesion sesion = listaSesion.get(table.getSelectedRow());
					bussinessLogic.cancelarReserva(sesion, user);			
				}
				else {
					System.out.println("no tienes nada seleccionado");
				}
			}
		});
		getContentPane().add(cancelarReservaBtn);
	}
}