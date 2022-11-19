package fr.ensimag.product;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Product[] findByIdIn(Long[] ids);

} 