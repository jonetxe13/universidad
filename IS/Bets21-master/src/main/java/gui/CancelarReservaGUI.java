package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		List<String> listaNomSesion = new ArrayList<String>();
		List<Integer> listaNumSala = new ArrayList<Integer>();
		List<Sesion> listaSesion = new ArrayList<Sesion>();
		
		for(String s: lista) {
			String[] nomSesion = s.split("/");
			listaNomSesion.add(nomSesion[0]);
			listaNumSala.add(Integer.parseInt(nomSesion[1]));
		}
		
		
		for(int i = 0; i < listaNomSesion.size(); i++) {
			if(bussinessLogic.getSesion(listaNomSesion.get(i), listaNumSala.get(i)) != null) {
				listaSesion.add(bussinessLogic.getSesion(listaNomSesion.get(i), listaNumSala.get(i)));
			}
		}
//		System.out.println(listaNomSesion);
//		System.out.println(listaNumSala);
		System.out.println(listaSesion);
		
		// Crear las columnas del JTable
		Vector<String> columns = new Vector<String>();
		columns.add("Fecha");
		columns.add("Plazas");
		columns.add("listaActividades");

		// Crear las filas del JTable
		Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
		Vector<Object> row = new Vector<Object>();
		for (Sesion sesion : listaSesion) {
//			if(sesion != null) {
				System.out.println(sesion.getFecha());
			    row.add(sesion.getFecha());
			    row.add(sesion.getPlazasDisponibles());
			    row.add(sesion.getListaActividades());
			    // ...
			    rows.add(row);
//			}
		}
		JScrollPane scrollPane = new JScrollPane();
		System.out.println(scrollPane.createHorizontalScrollBar());
		scrollPane.setHorizontalScrollBar(scrollPane.createHorizontalScrollBar());
		scrollPane.setVerticalScrollBar(scrollPane.createVerticalScrollBar());
		scrollPane.setBounds(0, 20, 210, 200);
		// Crear el JTable y añadirlo al JScrollPane
		JTable table = new JTable(rows, columns);
		table.setBounds(0, 20, 200, 190);
		getContentPane().add(table);
		getContentPane().add(scrollPane);
		
		JLabel error = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		error.setBounds(273, 140, 197, 13);
		error.setVisible(false);
		getContentPane().add(error);
		
		JButton cancelarReservaBtn = new JButton("Cancelar reserva");
		cancelarReservaBtn.setBounds(273, 61, 125, 38);
		cancelarReservaBtn.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					Sesion sesion = listaSesion.get(table.getSelectedRow());
					bussinessLogic.cancelarReserva(sesion, user);	
					row.remove(sesion);
					getContentPane().add(table);
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