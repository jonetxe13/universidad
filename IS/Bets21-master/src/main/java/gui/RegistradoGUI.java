package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.Usuario;

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
				@Override
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
				@Override
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