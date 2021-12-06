package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;


@RestController //classe para  API REST, retorna tudo em JSON e dispensa a anotação @ResponseBody, pois, já está contido nele
@RequestMapping(value = "/categories") // mapear solicitações HTTP
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity <List<Category>> findAll() { // ResponseEntity retorna respostasta de requisição web
		List<Category> List = service.findAll();
		return ResponseEntity.ok().body(List);
	}
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findByid(@PathVariable Long id ){ //@PathVariable parâmetro de método deve ser vinculado a uma variável de modelo URI "/{id}".
		Category obj =  service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	

}

//@RequestMapping(value = "/users", produces = { MediaType.APPLICATION_XML_VALUE }) // mapear solicitações HTTP