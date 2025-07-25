package ra.api.ss8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.api.ss8.model.entity.Dish;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
