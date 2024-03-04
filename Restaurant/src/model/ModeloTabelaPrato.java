package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaPrato extends AbstractTableModel{
	
	
	public ModeloTabelaPrato(ArrayList<Prato> pratos) {
		super();
		this.pratos = pratos;
	}

	private static final String[] colunas = {
			"ID", "Nome", "Ingredientes", "Descricao", "Preco"
	};
	
	private ArrayList<Prato> pratos;
	
	@Override
	public int getRowCount() {
		return pratos.size();
	}

	@Override
	public int getColumnCount() {

		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Prato prato = pratos.get(rowIndex);
		if(columnIndex == 0) {
			return prato.getId();
		}
		else if (columnIndex == 1) {
			return prato.getNome();
		}
		else if (columnIndex == 2) {
			return prato.getIngrediente();
		}
		else if (columnIndex == 3) {
			return prato.getDesc();
		}
		else if (columnIndex == 4) {
			return prato.getPreco();
		}
		else {
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
}
