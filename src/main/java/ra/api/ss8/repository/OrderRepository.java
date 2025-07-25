package ra.api.ss8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.api.ss8.model.entity.Order;
import ra.api.ss8.model.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.customer " +
            "JOIN FETCH o.employee " +
            "ORDER BY o.createdAt DESC")
    List<Order> findAllWithDetails();

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.customer " +
            "JOIN FETCH o.employee " +
            "JOIN FETCH o.orderDetails od " +
            "JOIN FETCH od.dish " +
            "WHERE o.id = :id")
    Order findByIdWithFullDetails(Long id);
}
