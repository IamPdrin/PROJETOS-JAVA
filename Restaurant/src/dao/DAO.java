package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import model.Prato;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	private static String CADASTRAR_PRATO = " INSERT INTO PRATO " + " (ID, NOME, INGREDIENTE, DESCRICAO, PRECO) " + " VALUES(NULL, ?, ?, ?, ?) ";
	
	private static String CONSULTAR_PRATO = " SELECT * FROM PRATO " + " WHERE ID = ? ";
	
	private static String LISTAR_PRATO  = " SELECT * FROM PRATO "  + " WHERE 1=1 ";	
	
	private static String ALTERAR_PRATO = " UPDATE PRATO SET" + " NOME = ?, INGREDIENTE = ?, DESCRICAO = ?, PRECO = ? " + " WHERE ID = ? ";
	
	private static String EXCLUIR_PRATO = " DELETE FROM PRATO " + " WHERE ID = ?"; 
	
	public DAO() {
		
	}
	
	
	
	public void cadastrarPrato (Prato prato) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		String query = CADASTRAR_PRATO;
		
		try {
			
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
		
			preparedStatement.setString(i++, prato.getNome());
			preparedStatement.setString(i++, prato.getIngrediente());
			preparedStatement.setString(i++, prato.getDesc());
			preparedStatement.setDouble(i++, prato.getPreco());
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Prato cadastrado com sucesso!");
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			fecharConexao();
		}
	}
	
	
	public Prato consultarPrato (String id) throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		Prato prato = null;
		String query = CONSULTAR_PRATO;
		
		try {
			int i = 1;
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(i++, id);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				prato = new Prato(resultSet.getString("id"),
						resultSet.getString("nome"),
						resultSet.getString("ingrediente"),
						resultSet.getString("descricao"),
						resultSet.getDouble("preco"));
			}
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			fecharConexao();
		}
		if (prato == null) {
			JOptionPane.showMessageDialog(null, "Não foi possível localizar esse prato", "Prato Não Identificado", JOptionPane.ERROR_MESSAGE);
			throw new Exception("Não foi possível localizar o prato selecionado");
		}
		return prato;
	}


	public void alterarPrato (String id, Prato prato) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = ALTERAR_PRATO;
		
		try {
			int i = 1;
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(i++, prato.getNome());
			preparedStatement.setString(i++, prato.getIngrediente());
			preparedStatement.setString(i++, prato.getDesc());
			preparedStatement.setDouble(i++, prato.getPreco());
			preparedStatement.setString(i++, id);
			
			preparedStatement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Prato alterado com sucesso!");
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
	}
	
	
	public void excluirPrato (String id) {
		Connection connection = Conexao.getInstancia().abrirConexao();
		
		String query = EXCLUIR_PRATO;
		
		try {
			int i = 1;
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(i++, id);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Prato excluido com sucesso!");
			
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			fecharConexao();
		}
		
	}
	
	
	public ArrayList<Prato> listarPratos() throws Exception {
		Connection connection = Conexao.getInstancia().abrirConexao();
		ArrayList<Prato> pratos = new ArrayList<>();
		String query = LISTAR_PRATO;
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				pratos.add(new Prato(
						resultSet.getString("ID"),
						resultSet.getString("NOME"),
						resultSet.getString("INGREDIENTE"),
						resultSet.getString("DESCRICAO"),
						resultSet.getDouble("PRECO")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		if (pratos.size() < 0) {
			JOptionPane.showMessageDialog(null, "Não há pratos cadastrados", "", JOptionPane.WARNING_MESSAGE);
			throw new Exception("Não há pratos cadastrados");
		}
		return pratos;
	}
	
	
	private void fecharConexao() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		Conexao.getInstancia().fecharConexao();
		} catch (SQLException e) {
				e.printStackTrace();
		}	
	}
	
}

