package br.com.projectjpa.services;

import br.com.projectjpa.exceptions.*;
import br.com.projectjpa.model.Seller;
import br.com.projectjpa.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository repository;

    public List<Seller> findAll() throws InternalServerErrorException {
        try {
            return repository.findAll();
        } catch (Exception error) {
            throw new InternalServerErrorException(error.getMessage());
        }
    }

    public Seller findById(long id) throws NotFoundException {
        Seller seller = repository.findById(id).orElse(null);

        if (seller == null) {
            throw new NotFoundException("Seller not found");
        }

        return seller;
    }

    public Seller insert(Seller obj) throws InternalServerErrorException, AlreadyExistsException {
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

    public Seller update(Long id, Seller obj) {
        try {
            Seller entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Seller entity, Seller obj) {
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
