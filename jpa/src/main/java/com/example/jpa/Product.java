package com.example.jpa;

import javax.persistence.*;

@Entity
//@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  //@Column(name = "name") regarder si nécessaire
  private String name;
  private Float price;
  private Long quantity;

  protected Product() {}

  public Product(String name, Float price, Long quantity) {
    this.name = name;
    this.price = price;
    this.quantity= quantity;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, name='%s', price='%f, quantity='%d']",
        id,name, price, quantity);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
		this.name = name;
	}

  public float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
		this.price = price;
	}

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}