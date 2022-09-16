package br.com.projectjpa.controller;

import br.com.projectjpa.entities.MessagePattern;
import br.com.projectjpa.exceptions.InternalServerErrorException;
import br.com.projectjpa.exceptions.NotFoundException;
import br.com.projectjpa.model.Product;
import br.com.projectjpa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        try {
            List<Product> products = productService.findAll();

            return new ResponseEntity(
                    products,
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
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);

            return new ResponseEntity(
                    product,
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
}
