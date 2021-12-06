package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.services.OrderService;


@RestController //Define classepara  API RESTful, retorna tudo em JSON e dispensa a anotação @ResponseBody, pois, já está contido nele
@RequestMapping(value = "/orders") // mapear solicitações HTTP
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity <List<Order>> findAll() { // ResponseEntity retorna respostast de requisição web
		List<Order> List = service.findAll();
		return ResponseEntity.ok().body(List);
	}
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findByid(@PathVariable Long id ){ //@PathVariable parâmetro de método deve ser vinculado a uma variável de modelo URI "/{id}".
		Order obj =  service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	// criar o método post order

}

//@RequestMapping(value = "/users", produces = { MediaType.APPLICATION_XML_VALUE }) // mapear solicitações HTTP