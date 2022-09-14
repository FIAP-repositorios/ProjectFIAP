package br.com.projectjpa.services;

import br.com.projectjpa.exceptions.InternalServerErrorException;
import br.com.projectjpa.exceptions.NotFoundException;
import br.com.projectjpa.model.Product;
import br.com.projectjpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() throws InternalServerErrorException {
        try {
            return repository.findAll();
        } catch (Exception error) {
            throw new InternalServerErrorException(error.getMessage());
        }
    }

    public Product findById(long id) throws NotFoundException {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            throw new NotFoundException("Product not found");
        }

        return product;
    }
}
