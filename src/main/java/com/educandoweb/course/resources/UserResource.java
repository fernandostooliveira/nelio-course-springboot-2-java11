package com.educandoweb.course.resources;

import java.net.URI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

/* Classe de recursos web correspondente a entidade User */


@RestController // Define classe para API RESTful, retorna tudo em JSON e dispensa a anotação
                // @ResponseBody, pois, já está contido nele
@RequestMapping(value = "/users") // mapear solicitações HTTP
public class UserResource {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> findAll() { // ResponseEntity retorna respostast de requisição
                                                // web
    List<User> List = service.findAll();
    return ResponseEntity.ok().body(List);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findByid(@PathVariable Long id) { // @PathVariable parâmetro de método
                                                                // deve ser vinculado a uma variável
                                                                // de modelo URI "/{id}".
    User obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<User> insert(@RequestBody User obj) {
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
    obj = service.update(id, obj);
    return ResponseEntity.ok().body(obj);
  }



}

// @RequestMapping(value = "/users", produces = { MediaType.APPLICATION_XML_VALUE }) // mapear
// solicitações HTTP
