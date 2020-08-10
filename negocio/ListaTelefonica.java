package negocio;
import java.util.List;
import java.util.Map;

import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import persistencia.ContatoDAO;

public class ListaTelefonica {
	private ContatoDAO contatoDAO=new ContatoDAO();
	
	public ListaTelefonica() {
		//contatoDAO.getAll();
	}
	
	public void adicionarContato(Contato contato) throws 
		ErroNaGravacaoException, ErroNaLeituraException, ContatoJaCadastradoException {
		contatoDAO.insert(contato);
	}
	
	public void removerContato(Contato contato) throws 
		ErroNaLeituraException, ErroNaGravacaoException, ContatoNaoCadastradoException {
		contatoDAO.delete(contato);
	}
	
	public List<Contato> buscarContato(Character letra) throws ErroNaLeituraException{
		
		
		return contatoDAO.getAll().get(letra);
	}
	
	public Map<Character, List<Contato>> listarContatos() throws ErroNaLeituraException{
		return contatoDAO.getAll();
	}
}
