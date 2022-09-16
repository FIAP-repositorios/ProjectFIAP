package br.com.projectjpa.services;

import br.com.projectjpa.exceptions.*;
import br.com.projectjpa.model.Buyer;
import br.com.projectjpa.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BuyerService {
    @Autowired
    private BuyerRepository repository;

    public List<Buyer> findAll() throws InternalServerErrorException {
        try {
            return repository.findAll();
        } catch (Exception error) {
            throw new InternalServerErrorException(error.getMessage());
        }
    }

    public Buyer findById(long id) throws NotFoundException {
        Buyer buyer = repository.findById(id).orElse(null);

        if (buyer == null) {
            throw new NotFoundException("Buyer not found");
        }

        return buyer;
    }

    public Buyer insert(Buyer obj) throws InternalServerErrorException, AlreadyExistsException {
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

    public Buyer update(Long id, Buyer obj) {
        try {
            Buyer entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Buyer entity, Buyer obj) {
        entity.setEmail(obj.getEmail());
        entity.setName(obj.getName());
        entity.setPassword(obj.getPassword());
        entity.setPhone(obj.getPhone());
        entity.setUsername(obj.getUsername());
        entity.setAdress(obj.getAdress());
        entity.setCep(obj.getCep());
        entity.setCnpj(obj.getCnpj());
    }
}
