package com.example.product;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  //List<Product> findByName(String Name);

} 