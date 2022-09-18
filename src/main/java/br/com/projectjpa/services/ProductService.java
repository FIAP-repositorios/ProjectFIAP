package br.com.projectjpa.services;

import br.com.projectjpa.exceptions.*;
import br.com.projectjpa.model.Product;
import br.com.projectjpa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public Product insert(Product obj) throws InternalServerErrorException, AlreadyExistsException {
        return repository.save(obj);
    }

    public Product update(Long id, Product obj) {
        try {
            Product entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setProductUrl(obj.getProductUrl());
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
