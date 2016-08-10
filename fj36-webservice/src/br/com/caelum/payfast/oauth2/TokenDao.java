package br.com.caelum.payfast.oauth2;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenDao {
	private List<String> acessToken = new ArrayList<String>();
	
	private List<String> authrizationCodes = new ArrayList<String>();
	
	public void adicionaAcessToken(String token) {
		acessToken.add(token);
	}
	
	public boolean existeAcessToken(String token) {
		return acessToken.contains(token);
	}
	
	public void adicionaAuthorizationCode(String code) {
		authrizationCodes.add(code);
	}
	
	public boolean existeAuthorizationCode(String code) {
		return authrizationCodes.contains(code);
	}
}
