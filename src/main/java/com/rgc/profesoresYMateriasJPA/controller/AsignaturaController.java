package com.rgc.profesoresYMateriasJPA.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.rgc.profesoresYMateriasJPA.model.Asignatura;
import com.rgc.profesoresYMateriasJPA.model.Docente;

public class AsignaturaController {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("profesoresymaterias");

	/** 
	 * 
	 */
	public static List<Asignatura> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM asignatura;", Asignatura.class);
		List<Asignatura> l = (List<Asignatura>) q.getResultList();

		em.close();

		return l;
	}
	
	
}
