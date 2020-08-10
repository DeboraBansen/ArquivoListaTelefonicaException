package persistencia;

import exceptions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dados.Contato;

public class ContatoDAO {
	private ArquivoContatoDAO arquivoContatoDAO=new ArquivoContatoDAO();
	
	public void insert(Contato contato) throws ErroNaGravacaoException,ErroNaLeituraException, ContatoJaCadastradoException {
		List<Contato> contatos=arquivoContatoDAO.leContatos();
		
		if(!contatos.contains(contato)) {
			arquivoContatoDAO.salvaContatos(contato);
		}else {
			throw new ContatoJaCadastradoException("Contato ja esta cadastrado");
			
		}
		
		
	}
	public void delete(Contato contato) throws ErroNaLeituraException,ErroNaGravacaoException, ContatoNaoCadastradoException {
		List<Contato> contatos=arquivoContatoDAO.leContatos();
		if(contatos.contains(contato)) {
			contatos.remove(contato);
			arquivoContatoDAO.salvaContatos(contatos);
		}else {
			throw new ContatoNaoCadastradoException("Contato nao estava cadastrado");
		}
		
		
	}
	
	
	
	public Map<Character, List<Contato>> getAll() throws ErroNaLeituraException{
		Map<Character, List<Contato>> mapContatos=new HashMap<Character, List<Contato>>();
		
		for(char i=65;i<91;i++) {
			List<Contato> lista=new LinkedList<Contato>();
			mapContatos.put(i, lista);
		}
		
		List<Contato> listContatos = arquivoContatoDAO.leContatos();
		
		for(Contato c :listContatos) {
			String nome=c.getNome().toUpperCase();
			List<Contato> contatosTemp=mapContatos.get(nome.charAt(0));
			contatosTemp.add(c);
		}
		return mapContatos;
	}
}
