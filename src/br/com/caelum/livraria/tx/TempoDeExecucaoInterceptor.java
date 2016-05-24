package br.com.caelum.livraria.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object imprimirLog(InvocationContext classe) throws Exception {
	    
		long inicio = System.currentTimeMillis();
				
		String nomeMetodo = classe.getMethod().getName();
		
		Object retorno = classe.proceed();
	
		long fim = System.currentTimeMillis();
		
		long resultado = fim - inicio;
		
		 System.out.println("Método executado: " + nomeMetodo + " - Tempo execução: " + resultado);
		
	    return retorno;
	 }
}
