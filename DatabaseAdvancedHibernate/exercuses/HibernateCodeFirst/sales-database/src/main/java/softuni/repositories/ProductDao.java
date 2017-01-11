package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Long>{
}
