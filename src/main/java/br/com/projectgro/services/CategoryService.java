package br.com.projectgro.services;

import br.com.projectgro.exceptions.InternalServerErrorException;
import br.com.projectgro.exceptions.NotFoundException;
import br.com.projectgro.model.Category;
import br.com.projectgro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Category findById(long id) throws NotFoundException {
        Category category = repository.findById(id)
                .orElse(null);

        if (category == null) {
            throw new NotFoundException("Category not found");
        }

        return category;
    }
}
