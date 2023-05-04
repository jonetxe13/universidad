package gui;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ListModel;

import domain.Sesion;

import java.awt.BorderLayout;
import java.util.List;

public class ConsultarSesionesGUI extends JFrame {
	public ConsultarSesionesGUI() {
		getContentPane().setLayout(null);
		List<Sesion> lista = RegistroGUI.getBusinessLogic().sesionesSemana();
		for(Sesion s: lista) System.out.println(s + " \n");
		Sesion[] sesionArray = lista.toArray(new Sesion[lista.size()]);
		JScrollPane panelSesiones = new JScrollPane();
		panelSesiones.setBounds(47, 36, 348, 188);
		JList<Sesion> jlist = new JList<>(sesionArray);
		panelSesiones.add(jlist);
		getContentPane().add(panelSesiones);
	}

}
