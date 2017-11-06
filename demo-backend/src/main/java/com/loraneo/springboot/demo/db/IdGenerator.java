package com.loraneo.springboot.demo.db;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

	@Autowired
	EntityManager em;

	public Long next() {
		return ((Number)em.createNativeQuery("SELECT nextval('demo.id_seq');").getSingleResult()).longValue();
	}
}
