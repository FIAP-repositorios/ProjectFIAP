package br.com.projectjpa.controller;

import br.com.projectjpa.entities.MessagePattern;
import br.com.projectjpa.exceptions.InternalServerErrorException;
import br.com.projectjpa.exceptions.NotFoundException;
import br.com.projectjpa.model.Category;
import br.com.projectjpa.model.Order;
import br.com.projectjpa.services.CategoryService;
import br.com.projectjpa.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        try {
            List<Category> list = categoryService.findAll();

            return new ResponseEntity(
                    list,
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
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = categoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
