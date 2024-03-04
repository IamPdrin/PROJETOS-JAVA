package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;
import model.ModeloTabelaPrato;
import model.Prato;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class JAdmArea extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblSubtitleAdm;
	private JButton btnCadastrarPrato;
	private JTable table;
	private ArrayList<Prato> pratos;
	private JAdmArea jAdmArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAdmArea frame = new JAdmArea();
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
	public JAdmArea() {
		setResizable(false);
		this.jAdmArea = this;
		DAO dao = new DAO();
		try {
			pratos = dao.listarPratos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		JLabel lblPrincipalAdm = new JLabel("Administrador");
		lblPrincipalAdm.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrincipalAdm.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPrincipalAdm.setBounds(276, 11, 258, 27);
		contentPane.add(lblPrincipalAdm);
		
		lblSubtitleAdm = new JLabel("Pratos Cadastrados");
		lblSubtitleAdm.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitleAdm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubtitleAdm.setBounds(324, 49, 159, 27);
		contentPane.add(lblSubtitleAdm);
		
		btnCadastrarPrato = new JButton("Cadastrar Prato");
		btnCadastrarPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jCadastro = new JCadastro(null, jAdmArea);
				jCadastro.setLocationRelativeTo(jCadastro);
				jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				jCadastro.setVisible(true);
			}
		});
		btnCadastrarPrato.setBounds(10, 384, 784, 23);
		contentPane.add(btnCadastrarPrato);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 784, 286);
		contentPane.add(scrollPane);
		
		ModeloTabelaPrato modeloTabela = new ModeloTabelaPrato(pratos);
		
		table = new JTable();
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 1) {
					try {
						Prato PratoSelecionado = dao.consultarPrato(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
						JCadastro jCadastro = new JCadastro(PratoSelecionado, jAdmArea);
						jCadastro.setLocationRelativeTo(jCadastro);
						jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastro.setVisible(true);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		

	}
}
		