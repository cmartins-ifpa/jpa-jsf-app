package controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Cliente;
import model.ClienteVO;
import utilitarios.FabricaConexao;

@Named
@SessionScoped
public class ExemploMB implements Serializable {
	private ClienteVO cliente = new ClienteVO();
	
	public ExemploMB() {}
	
	static EntityManager entityMgr = FabricaConexao.getConexao();
	
	@PostConstruct
	private void init() {
		this.cliente = new ClienteVO();
	   // System.out.println("@PostConstruct em jpa-jsf-app.");
       // entityMgr.close(); 
	}
	
	public String ok() {
		if (this.cliente.getId() == null) {
			System.out.println(" ID NULL");
			this.cliente = new ClienteVO();
		}
		System.out.println(" ID =  " + this.cliente.getId());
		Cliente c = entityMgr.find(Cliente.class, this.cliente.getId());
	    if (c != null) {
			this.cliente.setNome(c.getNome());
		} else 
			this.cliente.setNome("NAO LOCALIZOU O CLIENTE." ); 		
		// entityMgr.close();
		/**
		List<Cliente> clientes = getClientes();
		for (Cliente c1 : clientes) {
			System.out.println(c1.getNome() + " " + c1.getId());
		} 
		**/
		return "";
	}
	
	
	public ClienteVO getCliente() {
		return this.cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
}
