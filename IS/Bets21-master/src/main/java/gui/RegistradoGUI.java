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
import domain.Event;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;

public class RegistradoGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private Usuario user;
	
	/**
	 * This is the default constructor
	 */
	public RegistradoGUI(Usuario user) {
		this.user = user;
		initialize();
		
	}

	public void initialize() {
		getContentPane().setLayout(null);
		
		JButton reservar = new JButton("Reservar");
		reservar.setBounds(99, 40, 218, 45);
		reservar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ReservarGUI(user);
					a.setBounds(0, 0, 700, 600);
					a.setVisible(true);
				}
			});
		getContentPane().add(reservar);
		
		JButton cancelarReserva = new JButton("Cancelar Reserva");
		cancelarReserva.setBounds(99, 100, 218, 45);
		cancelarReserva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CancelarReservaGUI(user);
					a.setBounds(0, 0, 700, 600);
					a.setVisible(true);
				}
			});
		getContentPane().add(cancelarReserva);
		
		JLabel UsuarioIniciado = new JLabel("Hola " + user.getCorreo() + " desde aqui puedes controlar tus reservas y pagos.");
		UsuarioIniciado.setBounds(40, 10, 468, 25);
		getContentPane().add(UsuarioIniciado);
	}
}