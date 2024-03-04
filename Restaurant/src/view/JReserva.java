package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DAO;
import model.ModeloTabelaPrato;
import model.Prato;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class JReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
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
					JReserva frame = new JReserva();
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
	public JReserva() {

		setResizable(false);
		
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
		
		JLabel lblMesas = new JLabel("Mesas Disponíveis");
		lblMesas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMesas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesas.setBounds(621, 317, 132, 23);
		contentPane.add(lblMesas);
		
		JComboBox<String> cmbMesas = new JComboBox<>();
		cmbMesas.setBackground(new Color(255, 255, 255));
		cmbMesas.setModel(new DefaultComboBoxModel(new String[] {"mesa 1", "mesa 2", "mesa 3", "mesa 4", "mesa 5", "mesa 6", "mesa 7", "mesa 8", "mesa 9", "mesa 10"}));
		cmbMesas.setBounds(631, 351, 110, 23);
		contentPane.add(cmbMesas);
		
		JLabel lblCardapio = new JLabel("Cardápio");
		lblCardapio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardapio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCardapio.setBounds(215, 24, 132, 23);
		contentPane.add(lblCardapio);
		
		JLabel lblPedido = new JLabel("Pedido");
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPedido.setBounds(633, 24, 110, 23);
		contentPane.add(lblPedido);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(152, 267, 74, 23);
		contentPane.add(lblTotal);
		
		JLabel lblTotal1 = new JLabel("");
		lblTotal1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal1.setBounds(236, 267, 74, 23);
		contentPane.add(lblTotal1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 58, 524, 193);
		contentPane.add(scrollPane);
		
		listaModelo = new DefaultListModel<>();
		JList lista = new JList(listaModelo);
		lista.setBounds(572, 64, 232, 187);
		contentPane.add(lista);
		lista.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        selectedIndex = lista.getSelectedIndex();
		    }
		});
		
		ModeloTabelaPrato modeloTabela = new ModeloTabelaPrato(pratos);
		DecimalFormat df = 	new DecimalFormat("#0.00");
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
						lblTotal1.setText("R$ " + df.format(total));
						
					} catch (Exception e1){
						e1.printStackTrace();
					}
				}
			}
			
		});
		scrollPane.setViewportView(table);
		
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBackground(new Color(128, 255, 0));
		btnReservar.setForeground(new Color(255, 255, 255));
		btnReservar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaModelo.isEmpty() == true) {
					JOptionPane.showMessageDialog(contentPane, "Ainda não é possível reservar uma mesa", "Adicione itens ao pedido!", JOptionPane.ERROR_MESSAGE);
				} else {
					String mesa = (String) cmbMesas.getSelectedItem();
					cmbMesas.removeItem(mesa);
					JOptionPane.showMessageDialog(contentPane, "Direcione-se a " + mesa, "Pedido Realizado com Sucesso!", JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
					JPrincipal jPedido = new JPrincipal();
					jPedido.setLocationRelativeTo(jPedido);
					jPedido.setVisible(true);
				}
			}
		});	
		btnReservar.setBounds(20, 267, 122, 23);
		contentPane.add(btnReservar);		
		
		
		JButton btnRemoverItem = new JButton("Remover Item");
		btnRemoverItem.setForeground(new Color(255, 255, 255));
		btnRemoverItem.setBackground(new Color(255, 0, 0));
		btnRemoverItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemoverItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = lista.getSelectedIndex();
				
				if (listaModelo.isEmpty() == true) {
					JOptionPane.showMessageDialog(contentPane, "O pedido está vazio, não há o que remover.", "Adicione itens ao pedido!", JOptionPane.ERROR_MESSAGE);
				} else {
					
					if (selectedIndex != -1) {
						Prato pratoRemovido = pratos.get(selectedIndex);
						listaModelo.remove(selectedIndex);
						total -= pratoRemovido.getPreco();
						
						if (total < 0) {
							total = 0;
						}
						
						lblTotal1.setText("R$ " + df.format(total));
						
					} else { 
						JOptionPane.showMessageDialog(contentPane, "Selecione um item para remover.", "Nenhum item selecionado", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		btnRemoverItem.setBounds(621, 269, 132, 23);
		contentPane.add(btnRemoverItem);
		
	}
}
