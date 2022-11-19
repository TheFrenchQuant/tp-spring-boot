package fr.ensimag.invoice;

import java.util.*;
import javax.persistence.*;

@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String client;

  Map<Long, Long> order;

  protected Invoice() {}

  public Invoice(String client,  Map<Long, Long> order) {
    this.client = client;
    this.order = order;
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

  public Map<Long, Long> getOrder() {
    return order;
  }

  public void setOrder(Map<Long, Long> order) {
		this.order = order;
	}

  public Long getNumberOfProduct() {
    Long sum=0L;
    for (Long f : order.values()) {
      sum += f;
  }
    return sum;
  }

}