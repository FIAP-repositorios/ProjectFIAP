package br.com.projectgro.controller;

import br.com.projectgro.entities.MessagePattern;
import br.com.projectgro.exceptions.AlreadyExistsException;
import br.com.projectgro.exceptions.InternalServerErrorException;
import br.com.projectgro.exceptions.NotFoundException;
import br.com.projectgro.exceptions.ResourceNotFoundException;
import br.com.projectgro.model.Seller;
import br.com.projectgro.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> findAll() {
        try {
            List<Seller> sellers = sellerService.findAll();

            return new ResponseEntity(
                    sellers,
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
    public ResponseEntity<Seller> findById(@PathVariable Long id) {
        try {
            Seller seller = sellerService.findById(id);

            return new ResponseEntity(
                    seller,
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
    public ResponseEntity<Seller> save(@RequestBody Seller obj) {
        try {
            obj = sellerService.insert(obj);
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
            sellerService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException | DataIntegrityViolationException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Seller> update(@PathVariable Long id, @RequestBody Seller obj) {
        try {
            obj = sellerService.update(id, obj);
            return ResponseEntity.ok().body(obj);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
