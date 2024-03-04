package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPedido extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPedido frame = new JPedido();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPedido() {
	
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 457);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\Pichau\\eclipse-workspace\\Restaurant\\src\\Imagens\\IconT.png");
		setIconImage(icon.getImage());
		setTitle("IFome");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblPrincipal = new JLabel("Seja Bem-Vindo! ");
		lblPrincipal.setForeground(new Color(0, 0, 0));
		lblPrincipal.setBackground(new Color(255, 255, 255));
		lblPrincipal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrincipal.setBounds(300, 96, 207, 31);
		contentPane.add(lblPrincipal);
		
		JButton btnReservarMesa = new JButton("Comer no Restaurante");
		btnReservarMesa.setBackground(new Color(255, 0, 0));
		btnReservarMesa.setForeground(new Color(255, 255, 255));
		btnReservarMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JReserva JReserva = new JReserva();
				JReserva.setLocationRelativeTo(JReserva);
				JReserva.setVisible(true);
			}
		});
		btnReservarMesa.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReservarMesa.setBounds(154, 197, 207, 83);
		contentPane.add(btnReservarMesa);
		
		JButton btnEntrega = new JButton("Levar para Viagem");
		btnEntrega.setBackground(new Color(255, 0, 0));
		btnEntrega.setForeground(new Color(255, 255, 255));
		btnEntrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JEntrega jEntrega = new JEntrega();
				jEntrega.setLocationRelativeTo(jEntrega);
				jEntrega.setVisible(true);
			}
		});
		btnEntrega.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEntrega.setBounds(439, 197, 207, 83);
		contentPane.add(btnEntrega);
		
		JLabel lblSelecioneUmaDas = new JLabel("Selecione uma das opções abaixo");
		lblSelecioneUmaDas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneUmaDas.setForeground(Color.BLACK);
		lblSelecioneUmaDas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSelecioneUmaDas.setBackground(Color.WHITE);
		lblSelecioneUmaDas.setBounds(260, 142, 288, 31);
		contentPane.add(lblSelecioneUmaDas);
	}
}
