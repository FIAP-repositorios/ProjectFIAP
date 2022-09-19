package br.com.projectgro.controller;

import br.com.projectgro.entities.MessagePattern;
import br.com.projectgro.exceptions.AlreadyExistsException;
import br.com.projectgro.exceptions.InternalServerErrorException;
import br.com.projectgro.exceptions.NotFoundException;
import br.com.projectgro.exceptions.ResourceNotFoundException;
import br.com.projectgro.model.Buyer;
import br.com.projectgro.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/buyers")
public class BuyerController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping
    public ResponseEntity<List<Buyer>> findAll() {
        try {
            List<Buyer> buyers = buyerService.findAll();

            return new ResponseEntity(
                    buyers,
                    HttpStatus.OK
            );
        } catch (InternalServerErrorException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Buyer> findById(@PathVariable Long id) {
        try {
            Buyer buyer = buyerService.findById(id);

            return new ResponseEntity(
                    buyer,
                    HttpStatus.OK
            );
        } catch (NotFoundException error) {
            return new ResponseEntity(
                    new MessagePattern(error.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping
    public ResponseEntity<Buyer> insert(@RequestBody Buyer obj) {
        try {
            obj = buyerService.insert(obj);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

            return ResponseEntity.created(uri).body(obj);
        } catch (AlreadyExistsException error) {
            return new ResponseEntity(
                    new MessagePattern(error.getMessage()),
                    HttpStatus.CONFLICT
            );
        } catch (InternalServerErrorException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            buyerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException | DataIntegrityViolationException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Buyer> update(@PathVariable Long id, @RequestBody Buyer obj) {
        try {
            obj = buyerService.update(id, obj);
            return ResponseEntity.ok().body(obj);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
