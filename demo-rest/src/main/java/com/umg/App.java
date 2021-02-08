package com.umg;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umg.dao.personaDao;
import com.umg.entidad.Persona;

@RestController
@RequestMapping("persona")
public class App {
	
	//@GetMapping
	//@RequestMapping(value="/hello",method = RequestMethod.GET)
	
	@Autowired
	private personaDao dao;
	
	@RequestMapping(value="/listar",method = RequestMethod.GET)
	public ResponseEntity<List<Persona>>listar() {
		List<Persona> lista = dao.findAll();
		return  ResponseEntity.ok(lista);
	}
	
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<Persona>ObjetoPersona(@PathVariable("id") Long id) {
		Optional<Persona> persona = dao.findById(id);
		if(persona.isPresent()) {
			return  ResponseEntity.ok(persona.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona){
		Persona newPersona = dao.save(persona);
		return  ResponseEntity.ok(newPersona);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<String> eliminarPersona(@PathVariable("id") Long id){
		dao.deleteById(id);
		return  ResponseEntity.ok("Persona eliminada");
	}
	
	@PutMapping
	public ResponseEntity<Persona> actualizarPersona(@RequestBody Persona persona){
		Optional<Persona> p = dao.findById(persona.getId());
		if(p.isPresent()) {
			Persona updatePersona = p.get();
			updatePersona.setName(persona.getName());
			updatePersona.setApellidos(persona.getApellidos());
			dao.save(updatePersona);
			return  ResponseEntity.ok(updatePersona);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
