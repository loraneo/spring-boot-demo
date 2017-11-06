package com.loraneo.springboot.demo.dal;

import org.springframework.data.repository.CrudRepository;

import com.loraneo.springboot.demo.model.CommandStore;

public interface CommandStoreRepository extends CrudRepository<CommandStore, Long> {
    
}
