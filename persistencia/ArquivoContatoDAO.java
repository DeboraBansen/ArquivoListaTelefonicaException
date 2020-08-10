package persistencia;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import java.util.LinkedList;
import java.util.List;

import  dados.Contato;

public class ArquivoContatoDAO {
	
	private final String caminho="C:\\Users\\Debora\\eclipse-workspace\\ArquivoListaTelefonicaException\\src\\persistencia\\contatos.csv";
	private static EditorTexto arquivo=new EditorTexto();
	
	private String toCSV(Contato c) {
		String p="";
		
		p+=c.getNome()+",";
		p+=c.getTelefone();
		
		return p;
		
	}
	
	private Contato fromCSV(String s) {
		String[] atributos=s.split(",");
		
		Contato contato=new Contato();
		contato.setNome(atributos[0]);
		contato.setTelefone(Integer.parseInt(atributos[1]));
		
		return contato;
	}
	
	public List<Contato> stringToListaContato(List<String> dados){
		List<Contato> contatos=new LinkedList<Contato>();
		
		for(String linha:dados) {
			contatos.add(fromCSV(linha));
		}
		return contatos;
		
	}
	
	public List<Contato> leContatos() throws ErroNaLeituraException{
		
		return stringToListaContato(arquivo.leTexto(caminho));	
	}
	
	public List<String> ListaPessoaToString(List<Contato> contatos){
		List<String> arq=new LinkedList<String>();
		
		for(Contato c:contatos) {
			arq.add(toCSV(c));
		}
		return arq;
	}
	public void salvaContatos (List<Contato> contatos)throws ErroNaGravacaoException {
		arquivo.gravaTexto(caminho, ListaPessoaToString(contatos));
	}
	public void salvaContatos (Contato contato)  throws ErroNaGravacaoException {
		arquivo.gravaTexto(caminho, toCSV(contato));
	}
}
