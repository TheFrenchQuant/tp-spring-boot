package fr.ensimag.invoice;

import java.util.*;
import javax.persistence.*;

@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String client;

  @ElementCollection
  @CollectionTable(name = "productsOrder", joinColumns = @JoinColumn(name = "productsOrder_id"))
  @MapKeyColumn(name = "product_id")
  @Column(name = "quantity")
  Map<Long, Long> productsOrder;

  protected Invoice() {
  }

  public Invoice(String client, Map<Long, Long> productsOrder) {

    this.client = client;

    this.productsOrder = productsOrder;
  }

  @Override
  public String toString() {
    return productsOrder.toString();
  }

  public Long getId() {
    return id;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public Map<Long, Long> getProductsOrder() {
    return productsOrder;
  }

  public void setProductsOrder(Map<Long, Long> productsOrder) {
    this.productsOrder = productsOrder;
  }

  public Long NumberOfProduct() {
    Long sum = 0L;

    for (Long quantity : productsOrder.values()) {
      sum += quantity;
    }

    return sum;
  }

}