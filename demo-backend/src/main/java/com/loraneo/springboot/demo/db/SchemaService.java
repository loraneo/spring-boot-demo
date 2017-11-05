package com.loraneo.springboot.demo.db;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchemaService {

	@Autowired
	EntityManager em;

	public Long next() {
		return Long.valueOf(em.createNativeQuery("SELECT nextval() FROM demo.id_seq").getFirstResult());
	}
}
