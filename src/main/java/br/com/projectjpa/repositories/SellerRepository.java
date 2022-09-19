package br.com.projectjpa.repositories;

import br.com.projectjpa.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
