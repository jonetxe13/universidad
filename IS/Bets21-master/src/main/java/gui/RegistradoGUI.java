package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Usuario;

public class RegistradoGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private Usuario user;
	private JPanel jContentPane = null;

	/**
	 * This is the default constructor
	 */
	public RegistradoGUI(Usuario user) {
		this.user = user;
		initialize();
	}

	private void initialize() {
		this.setSize(700, 600);
		this.setContentPane(getJContentPane());
	}

	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);

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
		jContentPane.add(reservar);

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
		jContentPane.add(cancelarReserva);

		JLabel UsuarioIniciado = new JLabel("Hola " + user.getCorreo() + " desde aqui puedes controlar tus reservas y pagos.");
		UsuarioIniciado.setBounds(40, 10, 468, 25);
		jContentPane.add(UsuarioIniciado);
		
		JButton btnPagarFactura = new JButton("Pagar factura");
		btnPagarFactura.setBounds(99, 155, 218, 45);
		btnPagarFactura.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println(user.getCorreo());
					JFrame a = new PagarFacturasGUI(user);
					a.setBounds(0, 0, 700, 600);
					a.setVisible(true);
				}
			});
		jContentPane.add(btnPagarFactura);
		}
		return jContentPane;
	}
}