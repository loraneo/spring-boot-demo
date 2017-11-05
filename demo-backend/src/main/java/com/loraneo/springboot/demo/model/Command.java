package com.loraneo.springboot.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "demo", name = "command")
public class Command {

	@Id
	Long id;

	public Long getId() {
		return id;
	}

}
