package com.rgc.profesoresYMateriasJPA.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.rgc.profesoresYMateriasJPA.model.Asignatura;
import com.rgc.profesoresYMateriasJPA.model.Asignaturaspordocente;
import com.rgc.profesoresYMateriasJPA.model.Docente;

public class AsignaturasPorDocenteController {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("profesoresymaterias");

	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static Asignaturaspordocente findByAsignaturasPorDocente (int idDocente) {
		Asignaturaspordocente apd = null;
		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			Query q = em.createNativeQuery("SELECT * FROM asignaturaspordocente where idDocente = ? ", Asignaturaspordocente.class);
			q.setParameter(1, idDocente);
			apd = (Asignaturaspordocente) q.getSingleResult();
			em.close();
			
		} catch (Exception e2) {
		}
		
		return apd;
	}
	
	
	/**
	 * 
	 * @param v
	 */
	public static void update(Asignaturaspordocente apd) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(apd);
        System.out.println("He realizado la modificacion");
        em.getTransaction().commit();
        em.close();
    }
    
	/**
	 * 
	 * @param v
	 */
    public static void insert(Asignaturaspordocente apd) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(apd);
        System.out.println("He realizado la inserccion");
        em.getTransaction().commit();
        em.close();
    }
	
}
