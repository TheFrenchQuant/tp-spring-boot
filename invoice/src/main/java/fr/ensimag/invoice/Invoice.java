package fr.ensimag.invoice;

import java.util.*;
import javax.persistence.*;

@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String client;
  
  @Lob
  Map<String, Long> productsOrder;
  //private Long[] products;
  //private Long[] quantity;

  protected Invoice() {}

  public Invoice(String client,  Map<String, Long> productsOrder) {
    this.client = client;
    this.productsOrder = productsOrder;
  }

  // @Override
  // public String toString() {
  //   return String.format(
  //       "Customer[id=%d, name='%s', price='%f, quantity='%d']",
  //       id,name, price, quantity);
  // }

  public Long getId() {
    return id;
  }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
		this.client = client;
	}

  public Map<String, Long> getOrder() {
    return productsOrder;
  }

  public void setOrder(Map<String, Long> productsOrder) {
		this.productsOrder = productsOrder;
	}

  public Long getNumberOfProduct() {
    Long sum=0L;
    for (Long f : productsOrder.values()) {
      sum += f;
  }
    return sum;
  }

}