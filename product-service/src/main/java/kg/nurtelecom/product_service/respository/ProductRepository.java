package kg.nurtelecom.product_service.respository;

import kg.nurtelecom.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
