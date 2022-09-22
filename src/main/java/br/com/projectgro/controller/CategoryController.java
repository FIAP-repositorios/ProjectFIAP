package br.com.projectgro.controller;

import br.com.projectgro.entities.MessagePattern;
import br.com.projectgro.exceptions.AlreadyExistsException;
import br.com.projectgro.exceptions.InternalServerErrorException;
import br.com.projectgro.exceptions.NotFoundException;
import br.com.projectgro.exceptions.ResourceNotFoundException;
import br.com.projectgro.model.Category;
import br.com.projectgro.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        try {
            List<Category> categories = categoryService.findAll();

            return new ResponseEntity(
                    categories,
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
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            Category category = categoryService.findById(id);

            return new ResponseEntity(
                    category,
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
    public ResponseEntity<Category> save(@RequestBody Category obj) {
        try {
            obj = categoryService.insert(obj);
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
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException | DataIntegrityViolationException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj) {
        try {
            obj = categoryService.update(id, obj);
            return ResponseEntity.ok().body(obj);
        } catch (ResourceNotFoundException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
