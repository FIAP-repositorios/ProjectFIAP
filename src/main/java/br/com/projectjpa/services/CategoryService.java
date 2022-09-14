package br.com.projectjpa.services;

import br.com.projectjpa.exceptions.InternalServerErrorException;
import br.com.projectjpa.exceptions.NotFoundException;
import br.com.projectjpa.model.Category;
import br.com.projectjpa.repositories.CategoryRepository;
import br.com.projectjpa.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() throws InternalServerErrorException {
        try {
            List<Category> allCategories = repository.findAll();

            return allCategories;
        } catch (Exception error) {
            throw new InternalServerErrorException(error.getMessage());
        }
    }

    public Category findById(long id) {
        Optional<Category> obj = repository.findById(id);

        return obj.get();
    }
}
