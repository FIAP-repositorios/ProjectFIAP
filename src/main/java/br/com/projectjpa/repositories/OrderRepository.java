package br.com.projectjpa.repositories;

import br.com.projectjpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
