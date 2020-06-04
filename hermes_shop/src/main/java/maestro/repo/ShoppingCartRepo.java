package maestro.repo;

import maestro.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserId(UUID id);
}
