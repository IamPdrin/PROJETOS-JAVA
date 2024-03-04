package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DAO;
import model.Prato;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIngrediente;
	private JTextField txtDesc;
	private JTextField txtPreco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JCadastro(Prato PratoSelecionado, JAdmArea jAdmArea) {
		setResizable(false);
		
		DAO dao = new DAO();
		
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
		
		JLabel lblPrincipalCadastro = new JLabel(PratoSelecionado == null ? "Cadastrar Pratos" : "Alterar Pratos");
		lblPrincipalCadastro.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblPrincipalCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrincipalCadastro.setBounds(306, 56, 181, 23);
		contentPane.add(lblPrincipalCadastro);
		
		txtNome = new JTextField();
		txtNome.setBounds(282, 121, 302, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtIngrediente = new JTextField();
		txtIngrediente.setColumns(10);
		txtIngrediente.setBounds(282, 152, 302, 20);
		contentPane.add(txtIngrediente);
		
		txtDesc = new JTextField();
		txtDesc.setColumns(10);
		txtDesc.setBounds(282, 183, 302, 20);
		contentPane.add(txtDesc);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(282, 214, 302, 20);
		contentPane.add(txtPreco);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(191, 121, 81, 14);
		contentPane.add(lblNome);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIngredientes.setBounds(191, 152, 81, 14);
		contentPane.add(lblIngredientes);
		
		JLabel lblDesc = new JLabel("Descrição");
		lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDesc.setBounds(191, 183, 81, 14);
		contentPane.add(lblDesc);
		
		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setBounds(191, 214, 81, 14);
		contentPane.add(lblPreco);
		
		JButton btnIncluir = new JButton(PratoSelecionado == null ? "Incluir" : "Alterar");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double preco = Double.parseDouble(txtPreco.getText());
				Prato prato = new Prato(null, txtNome.getText(), txtIngrediente.getText(), txtDesc.getText(), preco);
				
				if(PratoSelecionado == null) {
					if(!"".equalsIgnoreCase(txtNome.getText().trim())) {
						dao.cadastrarPrato(prato);
						abrirTelaPrincipal(jAdmArea);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!");
					}	
					
				} else {
					if(!"".equalsIgnoreCase(txtNome.getText().trim())) {
						dao.alterarPrato(PratoSelecionado.getId(), prato);
						abrirTelaPrincipal(jAdmArea);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!");
					}
				}
			}
		});
		btnIncluir.setBounds(361, 263, 89, 23);	
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirPrato(PratoSelecionado.getId());
				abrirTelaPrincipal(jAdmArea);
			}
		});
		btnExcluir.setBackground(new Color(255, 0, 0));
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBounds(495, 263, 89, 23);
		btnExcluir.setVisible(false);
		contentPane.add(btnExcluir);
		
		if(PratoSelecionado != null) {
			preencherCampos(PratoSelecionado);
			btnExcluir.setVisible(true);
		}
	}
		
		
		private void preencherCampos(Prato PratoSelecionado) {
			txtNome.setText(PratoSelecionado.getNome());
			txtIngrediente.setText(PratoSelecionado.getIngrediente());
			txtDesc.setText(PratoSelecionado.getDesc());
			txtPreco.setText(String.valueOf(PratoSelecionado.getPreco()));
			
		}
		
		private void abrirTelaPrincipal(JAdmArea jAdmArea) {
			jAdmArea.dispose();
			dispose();
			jAdmArea = new JAdmArea();
			jAdmArea.setLocationRelativeTo(jAdmArea);
			jAdmArea.setVisible(true);
		}
}
