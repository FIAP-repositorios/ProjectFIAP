package br.com.projectgro.repositories;

import br.com.projectgro.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query(
            value = "update Order o set o.orderStatus = :status where o.id = :id"
    )
    Integer updateOrderStatus(Long id, int status);
}
