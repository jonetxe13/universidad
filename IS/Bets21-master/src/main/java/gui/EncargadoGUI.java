package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.Encargado;

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
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new PlanificarSesionesGUI(encargado);
				a.setBounds(0, 0, 700, 600);
				a.setVisible(true);
			}
		});
		getContentPane().add(reservar);

		JButton annadirActividad = new JButton("Annadir actividad");
		annadirActividad.setBounds(99, 90, 218, 45);
		annadirActividad.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				JFrame a = new AnnadirActividadesGUI();
				a.setBounds(0, 0, 700, 600);
				a.setVisible(true);
			}
		});
		getContentPane().add(annadirActividad);

		JLabel EncargadoIniciado = new JLabel(this.encargado.getCorreo() + " " + this.encargado.getContrasenna());
		EncargadoIniciado.setBounds(120, 10, 168, 25);
		getContentPane().add(EncargadoIniciado);
	}
}