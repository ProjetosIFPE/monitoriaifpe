package br.com.projetoperiodo.util.exception;

public class NegocioException extends ProjetoException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String message) {
		super(message);
	}
	
	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	public NegocioException( Throwable e ) {
		super(e);
	}
	
	public NegocioException() {
		super();
	}
	
	
	public NegocioException(String chave, Object param) {
		super(chave, param);
	}

	
	

	
	

}
