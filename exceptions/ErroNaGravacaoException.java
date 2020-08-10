package exceptions;

public class ErroNaGravacaoException extends ErroArquivoException {
	public ErroNaGravacaoException() {
		
	}
	public ErroNaGravacaoException(String mensagem) {
		super(mensagem);
	}
}
