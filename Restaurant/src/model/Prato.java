package model;

public class Prato {
	//Id, Preço, Descrição, Ingrediente e Nome:
	
	protected String id;
	protected double preco;
	protected String desc;
	protected String ingrediente;
	protected String nome;

	public Prato() {
		
	}
	
	//Construtor:
	//"ID", "Nome", "Ingredientes", "Descricao", "Preco"
	public Prato(String id, String nome, String ingrediente, String desc, double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.ingrediente = ingrediente;
		this.desc = desc;
		this.preco = preco;
	}

	
	//Getters e Setters:
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	
	
	
}
