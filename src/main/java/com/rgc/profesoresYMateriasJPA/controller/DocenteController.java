package com.rgc.profesoresYMateriasJPA.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.rgc.profesoresYMateriasJPA.model.Docente;


public class DocenteController {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("profesoresymaterias");

	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static List<Docente> findByLikeDescripcion (String descripcion) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM docente where lower(nombreCompleto) like ?", Docente.class);
		q.setParameter(1, "%" + descripcion.toLowerCase() + "%");	
		List<Docente> list = (List<Docente>) q.getResultList();
		
		em.close();
		return list;
	}
	
	
	
}
