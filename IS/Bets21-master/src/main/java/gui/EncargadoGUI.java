package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Encargado;
import domain.Event;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;

public class EncargadoGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private Encargado encargado;
	
	/**
	 * This is the default constructor
	 */
	public EncargadoGUI(Encargado encargado) {
		this.encargado = encargado;
		System.out.println(encargado);
		initialize();
	}

	public void initialize() {
		getContentPane().setLayout(null);
		
		JButton reservar = new JButton("Planificar sesiones");
		reservar.setBounds(99, 40, 218, 45);
		reservar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
//				BLFacade bussinesLogic = RegistroGUI.getBusinessLogic();
				JFrame a = new PlanificarSesionesGUI(encargado);
				a.setBounds(0, 0, 700, 600);
				a.setVisible(true);
			}
		});
		getContentPane().add(reservar);
		
		JLabel EncargadoIniciado = new JLabel(this.encargado.getCorreo() + " " + this.encargado.getContrasenna());
		EncargadoIniciado.setBounds(120, 10, 168, 25);
		getContentPane().add(EncargadoIniciado);
	}
}