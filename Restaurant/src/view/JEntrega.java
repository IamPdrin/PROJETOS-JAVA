package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Checkbox;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;
import model.ModeloTabelaPrato;
import model.Prato;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JEntrega extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtEndereco;
	private ArrayList<Prato> pratos;
	private JList<String> lista;
	private DefaultListModel<String> listaModelo;
	private int selectedIndex = -1;
	private double total = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JEntrega frame = new JEntrega();
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
	public JEntrega() {
		
		DecimalFormat df = 	new DecimalFormat("#0.00");
		
		DAO dao = new DAO();
		try {
			pratos = dao.listarPratos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		
		JLabel lblCardapio = new JLabel("Cardápio");
		lblCardapio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardapio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCardapio.setBounds(217, 43, 131, 21);
		contentPane.add(lblCardapio);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPedido.setBounds(613, 43, 131, 21);
		contentPane.add(lblPedido);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTotal.setBounds(384, 279, 68, 21);
		contentPane.add(lblTotal);
		
		JLabel lblTotal_1 = new JLabel("");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotal_1.setBounds(468, 281, 76, 21);
		contentPane.add(lblTotal_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 77, 524, 193);
		contentPane.add(scrollPane);
		
	
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEndereco.setBounds(20, 309, 68, 20);
		lblEndereco.setVisible(false);
		contentPane.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(94, 309, 175, 20);
		txtEndereco.setColumns(10);
		txtEndereco.setVisible(false);
		contentPane.add(txtEndereco);
		
		JLabel lblTaxa = new JLabel("R$ 5 de taxa adicionados");
		lblTaxa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTaxa.setBounds(394, 312, 150, 14);
		lblTaxa.setVisible(false);
		contentPane.add(lblTaxa);
		
		
		JCheckBox cbxEntrega = new JCheckBox("Viagem");
		cbxEntrega.addItemListener(new ItemListener() {
	
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (cbxEntrega.isSelected() == true){
					lblTotal_1.setText("R$ " + df.format(total += 5));
					lblTaxa.setVisible(true);
					lblEndereco.setVisible(true);
					txtEndereco.setVisible(true);
				}
				else {
					lblTotal_1.setText("R$ " + df.format(total -= 5));
					lblTaxa.setVisible(false);
					lblEndereco.setVisible(false);
					txtEndereco.setVisible(false);
				}
				
			}
		});
		
		
		cbxEntrega.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbxEntrega.setBackground(new Color(255, 255, 255));
		cbxEntrega.setBounds(20, 279, 175, 23);
		contentPane.add(cbxEntrega);
		
		
		listaModelo = new DefaultListModel<>();
		JList lista = new JList(listaModelo);
		lista.setBounds(582, 75, 193, 193);
		contentPane.add(lista);
		lista.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        selectedIndex = lista.getSelectedIndex();
		    }
		});
		
		ModeloTabelaPrato modeloTabela = new ModeloTabelaPrato(pratos);
		table = new JTable();
		table.setModel(modeloTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {
				if (e.getButton() == 1) {
					try {
						Prato PratoSelecionado = dao.consultarPrato(modeloTabela.getValueAt(table.getSelectedRow(), 0).toString());
						listaModelo.addElement(PratoSelecionado.getNome());
				
						total += PratoSelecionado.getPreco();
						lblTotal_1.setText("R$ " + df.format(total));
						
					} catch (Exception e1){
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnRealizarPedido = new JButton("Fazer Pedido");
		btnRealizarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaModelo.isEmpty() == true) {
					JOptionPane.showMessageDialog(contentPane, "Adicione itens à lista!", "Não foi possível finalizar o pedido", JOptionPane.ERROR_MESSAGE);
				}
				else if (cbxEntrega.isSelected()){
					if(txtEndereco.getText().isEmpty()) {
						JOptionPane.showMessageDialog(contentPane, "Preencha o campo de endereço!", "Erro ao finalizar o pedido", JOptionPane.ERROR_MESSAGE);
					} 
					else {
						JOptionPane.showMessageDialog(contentPane, "Seus itens serão enviados assim que prontos!", "Pedido realizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Você será notificado para retirar assim que pronto!", "Pedido realizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnRealizarPedido.setBackground(new Color(128, 255, 128));
		btnRealizarPedido.setForeground(new Color(255, 255, 255));
		btnRealizarPedido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRealizarPedido.setBounds(604, 322, 143, 23);
		contentPane.add(btnRealizarPedido);
		
		JButton btnRemover = new JButton("Remover Item");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = lista.getSelectedIndex();
				
				if (listaModelo.isEmpty() == true) {
					JOptionPane.showMessageDialog(contentPane, "Adicione itens ao pedido!", "O pedido está vazio", JOptionPane.ERROR_MESSAGE);
					total = 0;
				} else {
					
					if (selectedIndex != -1) {
						Prato pratoRemovido = pratos.get(selectedIndex);
						listaModelo.remove(selectedIndex);
						total -= pratoRemovido.getPreco();
						
						if (total < 0) {
							total = 0;
						}
						
						
						lblTotal_1.setText("R$ " + df.format(total));
						
					} else { 
						JOptionPane.showMessageDialog(contentPane, "Selecione um item para remover!", "Nenhum item selecionado", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRemover.setBackground(new Color(255, 0, 0));
		btnRemover.setForeground(new Color(255, 255, 255));
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemover.setBounds(604, 279, 143, 23);
		contentPane.add(btnRemover);
		
	}
}
