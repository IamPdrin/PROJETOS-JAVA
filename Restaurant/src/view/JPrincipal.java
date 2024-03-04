package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final JPrincipal Jprincipal = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPrincipal() {
		
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 794, 396);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrincipal = new JLabel("IFome");
		lblPrincipal.setForeground(new Color(255, 0, 0));
		lblPrincipal.setBounds(326, 11, 143, 47);
		lblPrincipal.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPrincipal);
		
		JButton btnFazerPedido = new JButton("Novo Pedido");
		btnFazerPedido.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFazerPedido.setBackground(new Color(255, 0, 0));
		btnFazerPedido.setForeground(new Color(255, 255, 255));
		
		btnFazerPedido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFazerPedido.setBackground(Color.white);
				btnFazerPedido.setForeground(Color.red);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnFazerPedido.setBackground(Color.red);
				btnFazerPedido.setForeground(Color.white);
			}
		});
		
		btnFazerPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				JPedido JPedido = new JPedido();
				JPedido.setLocationRelativeTo(JPedido);
				JPedido.setVisible(true);
				
			}
		});
		btnFazerPedido.setBounds(313, 170, 174, 71);
		panel.add(btnFazerPedido);
		
		JButton btnAdmArea = new JButton("ADM");
		btnAdmArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdmArea.setBackground(new Color(255, 0, 0));
		btnAdmArea.setForeground(new Color(255, 255, 255));
		btnAdmArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdmArea.setBackground(Color.white);
				btnAdmArea.setForeground(Color.red);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnAdmArea.setBackground(Color.red);
				btnAdmArea.setForeground(Color.white);
			}
		});
		btnAdmArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
					JLogin JLogin = new JLogin();
					JLogin.setLocationRelativeTo(JLogin);
					JLogin.setVisible(true);
			}
		});
		btnAdmArea.setBounds(714, 362, 70, 23);
		panel.add(btnAdmArea);
		
		ImageIcon icon2 = new ImageIcon("C:\\Users\\Pichau\\eclipse-workspace\\Restaurant\\src\\Imagens\\IconT.png");
		Image imagem = icon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon novoIcone = new ImageIcon(imagem);
		
		JLabel lblIcon = new JLabel(novoIcone);
		lblIcon.setBounds(359, 67, 80, 61);
		panel.add(lblIcon);
	
	}
}
