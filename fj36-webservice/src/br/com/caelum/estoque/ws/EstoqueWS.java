package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.ws.exception.AutorizacaoException;

@Stateless
@WebService(targetNamespace="http://caelum.com.br/estoquews/v1")
public class EstoqueWS {
	
	private Map<String, ItemEstoque> repositorio = new HashMap<String, ItemEstoque>();
	
	public EstoqueWS() {
		repositorio.put("SOA", new ItemEstoque("SOA", 5));
		repositorio.put("TDD", new ItemEstoque("TDD", 1));
		repositorio.put("RES", new ItemEstoque("RES", 2));
		repositorio.put("LOG", new ItemEstoque("LOG", 4));
		repositorio.put("WEB", new ItemEstoque("WEB", 1));
		repositorio.put("ARQ", new ItemEstoque("ARQ", 2));
	}
	
	@WebMethod(operationName="ItensPeloCodigo")
	@WebResult(name="ItemEstoque")
	public List<ItemEstoque> getItemsEstoque(@WebParam(name="codigo") List<String> codigos,
											 @WebParam(name="tokenUsuario", header=true) String token) throws AutorizacaoException {
		List<ItemEstoque> itens = new ArrayList<ItemEstoque>();
		
		if (token == null || !token.equals("TOKEN123")) {
			throw new AutorizacaoException("Não autorizado.");
		}
		
		if (codigos == null || codigos.isEmpty()) {
			return itens;
		}
		
		for (String codigo : codigos) {
			if (repositorio.containsKey(codigo)) {
				itens.add(repositorio.get(codigo));
			}
		}
		
		return itens;
	}

}