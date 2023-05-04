package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import domain.Usuario;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;

public class RegistradoGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	
	/**
	 * This is the default constructor
	 */
	public RegistradoGUI(String correo, String contrasenna) {
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(99, 40, 218, 45);
		getContentPane().add(btnNewButton);
		
		JLabel UsuarioIniciado = new JLabel(correo + " " + contrasenna);
		UsuarioIniciado.setBounds(120, 10, 168, 25);
		getContentPane().add(UsuarioIniciado);
		
	}
}