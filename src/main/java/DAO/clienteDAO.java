package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Cliente;

public class clienteDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoWEB");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	public void persist(Cliente cliente) {
		tx.begin();
		em.persist(cliente);
		tx.commit();
		em.close();
	}

	public void merge(Cliente cliente) {

		tx.begin();
		em.merge(cliente);
		tx.commit();
		em.close();
	}

	public void remove(Cliente cliente) {
			tx.begin();
			em.remove(cliente);
			tx.commit();
			em.close();
	}

	public Cliente find(Long cpf) {
		Query query = em.createQuery("Select c from Cliente c where cpf = " + cpf);
		Cliente cliente = null;
		try {
			cliente = (Cliente) query.getSingleResult();
			em.close();
			return cliente;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cliente;
	}

	public Cliente findOpen(Long cpf) {
		Query query = em.createQuery("Select c from Cliente c where cpf = " + cpf);
		Cliente cliente = null;
		try {
			cliente = (Cliente) query.getSingleResult();
			return cliente;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cliente;
	}

	public List<Cliente> findAll() {
		TypedQuery<Cliente> query = em.createQuery(""+"Select c from Cliente c ", Cliente.class);
		
		List<Cliente> clientes = query.getResultList();	
		
		return clientes;
	}

}
