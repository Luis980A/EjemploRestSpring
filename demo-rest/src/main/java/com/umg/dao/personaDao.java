package com.umg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.umg.entidad.Persona;

public interface personaDao extends JpaRepository<Persona, Long>{

}
