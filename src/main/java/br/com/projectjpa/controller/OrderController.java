package br.com.projectjpa.controller;

import br.com.projectjpa.entities.MessagePattern;
import br.com.projectjpa.exceptions.AlreadyExistsException;
import br.com.projectjpa.exceptions.InternalServerErrorException;
import br.com.projectjpa.exceptions.NotFoundException;
import br.com.projectjpa.model.Order;
import br.com.projectjpa.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        try {
            List<Order> orders = orderService.findAll();

            return new ResponseEntity(
                    orders,
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
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        try {
            Order order = orderService.findById(id);

            if (order == null) {
                return new ResponseEntity(
                        new MessagePattern("Order dont exists!"),
                        HttpStatus.NOT_FOUND
                );
            }

            return new ResponseEntity(
                    order,
                    HttpStatus.OK
            );
        } catch (Exception error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Order order) {
        try {
            Order orderCreated = orderService.save(order);

            return new ResponseEntity(
                    orderCreated,
                    HttpStatus.OK
            );
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
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            orderService.delete(id);

            return new ResponseEntity(
                    new MessagePattern(id + " Deleted with success!"),
                    HttpStatus.OK
            );
        } catch (NotFoundException error) {
            return new ResponseEntity(
                    new MessagePattern(error.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        } catch (InternalServerErrorException error) {
            return new ResponseEntity(
                    new MessagePattern("Internal server error \n" + error.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
