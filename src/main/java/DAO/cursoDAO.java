package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entidades.Cliente;
import entidades.Curso;

public class cursoDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoWEB");
	EntityManager em = emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();

	public void persist(Curso curso) {
		tx.begin();
		em.persist(curso);
		tx.commit();
		em.close();
	}

	public void merge(Curso curso) {

		tx.begin();
		em.merge(curso);
		tx.commit();
		em.close();
	}

	public void remove(Curso curso) {

		tx.begin();
		em.remove(curso);
		tx.commit();
		em.close();

	}

	public Curso find(int cdcurso) {
		Query query = em.createQuery("Select c from Curso c where cdcurso = " + cdcurso);
		Curso curso = null;
		try {
			curso = (Curso) query.getSingleResult();
			em.close();
			return curso;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return curso;
	}

	public Curso findOpen(int cdcurso) {
		Query query = em.createQuery("Select c from Curso c where cdcurso = " + cdcurso);
		Curso curso = null;
		try {
			curso = (Curso) query.getSingleResult();
			return curso;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return curso;
	}

	public List<Curso> findAll() {
		TypedQuery<Curso> query = em.createQuery("" + "Select c from Curso c ", Curso.class);

		List<Curso> cursos = query.getResultList();

		return cursos;
	}

}
