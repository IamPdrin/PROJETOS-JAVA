package view;

import java.awt.EventQueue;

import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setVisible(true);
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
	public JLogin() {
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
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(10, 11, 794, 396);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrincipalLogin = new JLabel("Login Adm");
		lblPrincipalLogin.setForeground(new Color(0, 0, 0));
		lblPrincipalLogin.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPrincipalLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrincipalLogin.setBounds(294, 56, 200, 31);
		panel.add(lblPrincipalLogin);
		
		txtUser = new JTextField();
		txtUser.setBounds(335, 156, 117, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblUser = new JLabel("Usuário");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setForeground(new Color(0, 0, 0));
		lblUser.setBounds(335, 131, 76, 14);
		panel.add(lblUser);
		
		JLabel lblPassword = new JLabel("Senha");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setBounds(335, 187, 61, 27);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(335, 216, 117, 20);
		panel.add(txtPassword);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtUser.getText() != null && !txtUser.getText().isEmpty() && txtPassword.getText() != null && !txtPassword.getText().isEmpty()){
					if(txtPassword.getText().equals("123")) {
						JOptionPane.showMessageDialog(btnEntrar, "Login Realizado com sucesso");
						dispose();
						JAdmArea JAdmArea = new JAdmArea();
						JAdmArea.setLocationRelativeTo(JAdmArea);
						JAdmArea.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(btnEntrar, "Informações Inválidas", "Senha Incorreta", JOptionPane.ERROR_MESSAGE);
						txtUser.setText("");
						txtPassword.setText("");
					}
				}
				else {
					JOptionPane.showMessageDialog(btnEntrar, "Preencha todo os campos", "Aviso", JOptionPane.WARNING_MESSAGE);
					txtUser.setText("");
					txtPassword.setText("");
				}  
			}
		});
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setBorder(new LineBorder(Color.WHITE, 2));
		btnEntrar.setBounds(335, 279, 117, 23);
		panel.add(btnEntrar);
	}
}
