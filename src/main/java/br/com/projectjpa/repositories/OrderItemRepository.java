package br.com.projectjpa.repositories;

import br.com.projectjpa.model.OrderItem;
import br.com.projectjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
