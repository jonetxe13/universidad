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
import domain.Usuario;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;

public class RegistrarseGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField correoTextField;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;
	
	/**
	 * This is the default constructor
	 */
	public RegistrarseGUI() {
		getContentPane().setLayout(null);
		
		correoTextField = new JTextField();
		correoTextField.setBounds(157, 48, 96, 19);
		getContentPane().add(correoTextField);
		correoTextField.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(157, 98, 96, 19);
		getContentPane().add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(157, 154, 96, 19);
		getContentPane().add(passwordField2);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(172, 176, 103, 34);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				BLFacade bussinesLogic = RegistroGUI.getBusinessLogic();
//				System.out.println(bussinesLogic.userExists(correoTextField.getText(), passwordField1.getText()));
				if(bussinesLogic.userExists(correoTextField.getText(), passwordField1.getText())) {
					System.out.print("Ese usuario ya existe");
//					JFrame a = new RegistradoGUI(textField.getText(), passwordField.getText());
//					a.setVisible(true);
				}
				else {
					if(passwordField1.equals(passwordField2)) {
						System.out.println("Las contrasennas tienen que ser iguales");
					}
					bussinesLogic.createUsuario(correoTextField.getText(), passwordField1.getText());
					System.out.print("se ha creado el usuario\n");
					System.out.println(bussinesLogic.userExists(correoTextField.getText(), passwordField1.getText()));
					
				}
//				JFrame a = new CreateQuestionGUI(new Vector<Event>());
//				a.setVisible(true);
			}
		});
		getContentPane().add(btnNewButton);
		
	}
}