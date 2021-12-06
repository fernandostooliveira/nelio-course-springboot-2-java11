package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;


@RestController //Define classepara  API RESTful, retorna tudo em JSON e dispensa a anotação @ResponseBody, pois, já está contido nele
@RequestMapping(value = "/products") // mapear solicitações HTTP
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
	public ResponseEntity <List<Product>> findAll() { // ResponseEntity retorna respostast de requisição web
		List<Product> List = service.findAll();
		return ResponseEntity.ok().body(List);
	}
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findByid(@PathVariable Long id ){ //@PathVariable parâmetro de método deve ser vinculado a uma variável de modelo URI "/{id}".
		Product obj =  service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	

}

//@RequestMapping(value = "/users", produces = { MediaType.APPLICATION_XML_VALUE }) // mapear solicitações HTTP