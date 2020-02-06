package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Curso;
import entidades.Pagamento;

public class pagamentoDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoWEB");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	public void persist(Pagamento pagamento) {
		tx.begin();
		em.persist(pagamento);
		tx.commit();
		em.close();
	}

	public void merge(Pagamento pagamento) {
		tx.begin();
		em.persist(pagamento);
		tx.commit();
		em.close();
	}

	public void remove(Pagamento pagamento) {
		tx.begin();
		em.remove(pagamento);
		tx.commit();
		em.close();
	}

	public Pagamento find(long cpf, int cdcurso) {
		Query query = em.createQuery("Select c from Pagamento c where cdcurso = " + cdcurso + " and cpf = " + cpf);
		Pagamento pagamento = null;
		try {
			pagamento = (Pagamento) query.getSingleResult();
			em.close();
			return pagamento;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return pagamento;
	}

	public Pagamento findOpen(long cpf, int cdcurso) {
		Query query = em.createQuery("Select c from Pagamento c where cdcurso = " + cdcurso + " and cpf = " + cpf);
		Pagamento pagamento = null;
		try {
			pagamento = (Pagamento) query.getSingleResult();
			return pagamento;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return pagamento;
	}

	public List<Pagamento> findAll() {
		TypedQuery<Pagamento> query = em.createQuery("" + "Select p from Pagamento p ", Pagamento.class);

		List<Pagamento> pagamentos = query.getResultList();

		return pagamentos;
	}

}
