package gui;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import businessLogic.BLFacade;

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
		correoTextField.setBounds(207, 43, 116, 28);
		getContentPane().add(correoTextField);
		correoTextField.setColumns(10);

		passwordField1 = new JPasswordField();
		passwordField1.setBounds(207, 93, 116, 28);
		getContentPane().add(passwordField1);

		passwordField2 = new JPasswordField();
		passwordField2.setBounds(207, 149, 116, 28);
		getContentPane().add(passwordField2);

		JLabel error = new JLabel("error"); //$NON-NLS-1$
		error.setBounds(207, 180, 397, 13);
		error.setVisible(false);
		getContentPane().add(error);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.setBounds(207, 205, 116, 34);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//tienes que introducir la contrasenna dos veces para verificarla y te crea el usuario
				BLFacade bussinesLogic = RegistroGUI.getBusinessLogic();
				if(bussinesLogic.userExists(correoTextField.getText(), passwordField1.getText())) {
					error.setText("Ese usuario ya existe");
					error.setVisible(true);
				}
				else {
					if(!correoTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}")) {
						error.setText("Ejemplo correo: example@example.com");
						error.setVisible(true);
					}
					else if(!(passwordField1.getText().equals(passwordField2.getText()))) {
						error.setText("Las contrasennas tienen que ser iguales");
						error.setVisible(true);
					}
					else if(passwordField1.getText().equals(passwordField2.getText()) && passwordField1.getText().equals("")) {
						error.setText("Tienes que introducir contraseña");
						error.setVisible(true);
					}
					else {
						bussinesLogic.createUsuario(correoTextField.getText(), passwordField1.getText());
						error.setText("se ha creado el usuario\n");
						error.setVisible(true);
						JFrame a = new RegistradoGUI(bussinesLogic.createUsuario(correoTextField.getText(), passwordField1.getText()));
						a.setBounds(0, 0, 700, 600);
						a.setVisible(true);
					}
				}
			}
		});
		getContentPane().add(btnNewButton);

		JLabel lblCorreo = new JLabel("Correo electronico:"); //$NON-NLS-1$
		lblCorreo.setBounds(51, 51, 146, 13);
		getContentPane().add(lblCorreo);

		JLabel lblContraseña = new JLabel("Introducir contraseña:"); //$NON-NLS-1$
		lblContraseña.setBounds(51, 101, 146, 13);
		getContentPane().add(lblContraseña);

		JLabel lblRepetirContr = new JLabel("Repetir contraseña:"); //$NON-NLS-1$
		lblRepetirContr.setBounds(51, 157, 145, 13);
		getContentPane().add(lblRepetirContr);

	}
}