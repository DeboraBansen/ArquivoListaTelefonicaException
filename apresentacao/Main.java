package apresentacao;
import java.util.Scanner;


import dados.Contato;
import exceptions.ContatoJaCadastradoException;
import exceptions.ContatoNaoCadastradoException;
import exceptions.ErroNaGravacaoException;
import exceptions.ErroNaLeituraException;
import negocio.ListaTelefonica;

public class Main {
	private static ListaTelefonica listaTelefonica=new ListaTelefonica();
	private static Scanner s=new Scanner(System.in);
	
	public static void main(String[] args) {
		menu();
		

	}
	public static void imprimeMenu() {
		System.out.println("Escolha uma opcao: ");
		System.out.println("0- Sair");
		System.out.println("1- Cadastrar Contato");
		System.out.println("2- Remover Contato");
		System.out.println("3- Exibir Contatos");
	}
	
	public static void menu() {
		int opcao =-1;
		while(opcao!=0) {
			
			imprimeMenu();
			opcao=s.nextInt();
			
			switch(opcao) {
			case 0:
				break;
			case 1:
				Contato contato=novoContato();
				try {
					listaTelefonica.adicionarContato(contato);
				} catch (ErroNaGravacaoException  e) {
					System.err.println(e.getMessage());
					System.err.println("Caminho informado : "+e.getCaminho());
				}catch (ErroNaLeituraException e) {
					System.err.println(e.getMessage());
					System.err.println("Caminho informado : "+e.getCaminho());
				}catch (ContatoJaCadastradoException e) {
					System.err.println(e);
				}
				
				break;
			case 2:
				removerContato();
				break;
			case 3:
				exibirContatos();
				break;
			default:
				System.err.println("Numero invalido!");
				break;
				
			}
		}
	}
	
	public static Contato novoContato() {
		Contato c=new Contato();
		
		System.out.println("Digite o nome do contato:");
		c.setNome(s.next());
		
		System.out.println("Digite o numero de telefone:");
		c.setTelefone(s.nextInt());
		
		return c;
	}
	
	public static void removerContato() {
		System.out.println("Digite a primeira letra do contato que deseja remover:");
		String entrada=s.next().toUpperCase();
		
		try {
			if(listaTelefonica.buscarContato(entrada.charAt(0)).size()>0) {
				mostrarContatos(entrada.charAt(0));
				System.out.println("Digite o numero do contato que deseja remover:");
				int escolha=s.nextInt();
				if(escolha<listaTelefonica.buscarContato(entrada.charAt(0)).size()) {
					listaTelefonica.removerContato(listaTelefonica.buscarContato(entrada.charAt(0)).get(escolha));
				}else {
					System.out.println("Contato nao removido. Valor maior que a quantidade de contatos na lista");
				}
			}else {
				System.out.println("Nao existem contatos para serem removidos");
			}
		}
		 catch (ErroNaGravacaoException  e) {
			System.err.println(e.getMessage());
			System.err.println("Caminho informado : "+e.getCaminho());
		}catch (ErroNaLeituraException e) {
			System.err.println(e.getMessage());
			System.err.println("Caminho informado : "+e.getCaminho());
		}catch (ContatoNaoCadastradoException e) {
			System.err.println(e);
		}
	}
	
	public static void mostrarContatos(Character letra) {
		try {
			for(int i=0;i<listaTelefonica.buscarContato(letra).size();i++) {
				System.out.println("Contato "+i);
				System.out.println(listaTelefonica.buscarContato(letra).get(i).toString());
				System.out.println();
			}
		} catch (ErroNaLeituraException e) {
			System.err.println(e.getMessage());
			System.err.println("Caminho informado : "+e.getCaminho());
		}
	}
	
	public static void exibirContatos() {
		
		try {
			listaTelefonica.listarContatos().forEach((chave,lista)->{
				System.out.println(chave+":");
				for(Contato contato: lista) {
					System.out.println(" "+contato.toString());
				}
			});
		} catch (ErroNaLeituraException e) {
			System.err.println(e.getMessage());
			System.err.println("Caminho informado : "+e.getCaminho());
		}
		
	}

}
