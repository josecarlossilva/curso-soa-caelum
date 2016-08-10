package br.com.caelum.estoque.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EstoqueRmi extends Remote {

	public ItemEstoque getItemsEstoque(String codigoProduto) throws RemoteException;
	
}
