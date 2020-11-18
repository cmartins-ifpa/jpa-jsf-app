package controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.ClienteDAO;
import entidades.Cliente;
import model.ClienteVO;
import utilitarios.FabricaConexao;

@Named 
@RequestScoped
public class ClienteMB implements Serializable {
	private ClienteVO cliente = new ClienteVO();	
	private List<Cliente> clientes;
	
	static ClienteDAO dao = new ClienteDAO();
	
	public ClienteMB() {}
 	
	public String cadastrar() {
		System.out.println("vo = " + cliente.toString() );
		boolean incluiu = dao.incluir(cliente);
		if (incluiu)
		   FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrado com ID="+cliente.getId(), null));
		else
		   FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro na Operação!", null));
		// limpa o "VO" para incluir um novo
		this.cliente = new ClienteVO();
		return "";
	}
	
	// getters e setters
	public ClienteVO getCliente() {
		return this.cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		this.clientes = dao.getClientes();
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
		
}
