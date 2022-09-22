package br.com.projectgro.services;

import br.com.projectgro.exceptions.*;
import br.com.projectgro.model.Category;
import br.com.projectgro.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Category insert(Category obj) throws InternalServerErrorException, AlreadyExistsException {
        return repository.save(obj);
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

    public Category update(Long id, Category obj) {
        try {
            Category entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }
}
