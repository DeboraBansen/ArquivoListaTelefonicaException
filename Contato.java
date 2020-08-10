package dados;

public class Contato {
	private String nome;
	private int telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public boolean equals(Object o) {
		Contato c= (Contato)(o);
		
		return c.getTelefone()==this.telefone;
	}
	public String toString() {
		return "Nome = "+this.nome+"\n"+
				"Telefone = "+this.telefone+"\n";
	}
	
}
