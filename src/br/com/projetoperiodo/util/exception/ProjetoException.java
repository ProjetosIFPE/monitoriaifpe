package br.com.projetoperiodo.util.exception;

import java.util.HashMap;

public class ProjetoException extends Exception {

	private HashMap<String, Object> parametrosErro = new HashMap<>();
	
	
	public ProjetoException(String message, Throwable cause) {
		super(message, cause);
	}

	
	public ProjetoException(String message) {
		super(message);
	}
	
	
	public ProjetoException(String chave, Object param) {
		parametrosErro.put(chave, param);
	}
	
	public HashMap<String, Object> getParametrosDeErro() {
		return this.parametrosErro;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7593245636297325333L;

}
