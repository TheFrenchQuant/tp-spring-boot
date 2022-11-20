package fr.ensimag.invoice;

import java.util.*;
import javax.persistence.*;

@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String client;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(name = "raw_events_custom", joinColumns = @JoinColumn(name = "raw_event_id"))
  @MapKeyColumn(name = "field_key", length = 50)
  @Column(name = "field_val", length = 100)
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