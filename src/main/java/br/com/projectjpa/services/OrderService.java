package br.com.projectjpa.services;

import br.com.projectjpa.exceptions.AlreadyExistsException;
import br.com.projectjpa.exceptions.InternalServerErrorException;
import br.com.projectjpa.exceptions.NotFoundException;
import br.com.projectjpa.model.Order;
import br.com.projectjpa.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() throws InternalServerErrorException {
        try {
            return repository.findAll();
        } catch (Exception error) {
            throw new InternalServerErrorException(error.getMessage());
        }
    }

    public Order findById(long id) {
        Order order = repository.findById(id).orElse(null);

        return order;
    }

    public Order save(Order order) throws InternalServerErrorException, AlreadyExistsException {
        Order orderExists = this.findById(order.getId());

        if (orderExists == null) {
            try {
                return repository.save(order);
            } catch (Exception error) {
                throw new InternalServerErrorException(error.getMessage());
            }
        } else {
            throw new AlreadyExistsException("Order already exists");
        }
    }
}
