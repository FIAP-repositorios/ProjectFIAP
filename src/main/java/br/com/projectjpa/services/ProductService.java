package br.com.projectjpa.services;

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

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }
}
