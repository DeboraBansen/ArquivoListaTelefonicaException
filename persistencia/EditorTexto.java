package persistencia;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;

import java.util.LinkedList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditorTexto {
	
	public List<String> leTexto(String caminho) throws ErroNaLeituraException{
		List<String> dados=new LinkedList<String>();
		
		FileReader arq;
		BufferedReader lerArq;
		
		try {
			arq=new FileReader(caminho);
			lerArq=new BufferedReader(arq);
			String s=lerArq.readLine();
			
			while(s!=null) {
				dados.add(s);
				s=lerArq.readLine();
			}
			arq.close();
		}catch(IOException e) {
			ErroNaLeituraException erro=new ErroNaLeituraException("Erro na leitura");
			erro.setCaminho(caminho);
			throw erro;
		}
		return dados;
	}
	
	public void gravaTexto(String caminho,List<String> dados) throws ErroNaGravacaoException {
		FileWriter arq;
		try {
			arq=new FileWriter(caminho);
			for(String i:dados) {
				arq.write(i+"\n");
			}
			arq.close();
		}catch(IOException e) {
			ErroNaGravacaoException erro= new ErroNaGravacaoException("Erro na gravacao dos contatos");
			erro.setCaminho(caminho);
			throw erro;
		}
	}
	
	public void gravaTexto(String caminho,String linha) throws ErroNaGravacaoException {
		FileWriter arq;
		
		try {
			arq=new FileWriter(caminho,true);
			arq.write(linha+"\n");
			arq.close();
			
		}catch(IOException e) {
			ErroNaGravacaoException erro= new ErroNaGravacaoException("Erro na gravacao dos contatos");
			erro.setCaminho(caminho);
			throw erro;
		}
	}
}
