package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Usuario;

public class RegistroGUI extends JFrame{
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * This is the default constructor
	 */
	public RegistroGUI() {
		super();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initialize() {
		this.setSize(700, 600);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);

			textField = new JTextField();
//			textField.setText(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
			textField.setBounds(146, 45, 166, 34);
			jContentPane.add(textField);
			textField.setColumns(10);

			JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel.setBounds(67, 55, 46, 14);
			jContentPane.add(lblNewLabel);

			JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel_1.setBounds(67, 111, 69, 14);
			jContentPane.add(lblNewLabel_1);

			JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblNewLabel_2.setBounds(191, 20, 96, 14);
			jContentPane.add(lblNewLabel_2);

			passwordField = new JPasswordField();
//			passwordField.setText(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.passwordField.text")); //$NON-NLS-1$ //$NON-NLS-2$
			passwordField.setBounds(146, 100, 166, 28);
			jContentPane.add(passwordField);
//			ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.lblNewLabel_3.text")
			JLabel registrarseLBL = new JLabel("**Registrarse**"); //$NON-NLS-1$
			registrarseLBL.setBounds(146, 139, 90, 14);
			registrarseLBL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JFrame a = new RegistrarseGUI();
					a.setBounds(0, 0, 700, 600);
					a.setVisible(true);
				}
			});
			jContentPane.add(registrarseLBL);

			JLabel error = new JLabel("El usuario no existe o es incorrecto"); //$NON-NLS-1$
			error.setBounds(245, 140, 197, 13);
			error.setVisible(false);
			jContentPane.add(error);
//			if(appFacadeInterface.getActividades() != null) {
//				System.out.println("todo gucci");
//			}
			
			JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.setBounds(169, 179, 115, 34);
			btnNewButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// Cuando haces click mira si el usuario existe, si existe crea una ventana con el usuario registrado

					if(appFacadeInterface.encargadoExists(textField.getText(), passwordField.getText())) {
						error.setText("el encargado se ha encontrado");
						error.setVisible(true);
						System.out.println(appFacadeInterface.getEncargado(textField.getText(), passwordField.getText()));
						JFrame a = new EncargadoGUI(appFacadeInterface.getEncargado(textField.getText(), passwordField.getText()));
						a.setBounds(0, 0, 500, 400);
						a.setVisible(true);

					}
					else if(appFacadeInterface.userExists(textField.getText(), passwordField.getText())) {
						error.setText("Se ha encontrado el usuario");
						error.setVisible(true);
						Usuario user = appFacadeInterface.createUsuario(textField.getText(), passwordField.getText());
						JFrame a = new RegistradoGUI(user);
						a.setBounds(0, 0, 500, 400);
						a.setVisible(true);
					}
					else {
						error.setVisible(true);
						jContentPane.add(error);
					}
				}
			});
			jContentPane.add(btnNewButton);

			JButton btnConsultarSesiones = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RegistroGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnConsultarSesiones.setBounds(23, 176, 119, 40);
			btnConsultarSesiones.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ConsultarSesionesGUI();
					a.setBounds(0, 0, 500, 400);
					a.setVisible(true);
				}
			});
			jContentPane.add(btnConsultarSesiones);


		}
		return jContentPane;
	}
}