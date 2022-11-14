package com.example.produit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  private Float price;
  private Long quantity;

  protected Produit() {}

  public Produit(String name, Float price, Long quantity) {
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

  public float getPrice() {
    return price;
  }

  public Long getQuantity() {
    return quantity;
  }
}