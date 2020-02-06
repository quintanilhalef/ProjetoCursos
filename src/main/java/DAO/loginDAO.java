package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Curso;
import entidades.Login;

public class loginDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoWEB");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	
	public Login find(long cpf, String senha) {
		Query query = em.createQuery("Select l from Login l where cpf = "+cpf+" and senha = '"+senha+"'");
		Login login = null;
		try {
			login = (Login) query.getSingleResult();
			em.close();
			return login;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return login;
	}
}
