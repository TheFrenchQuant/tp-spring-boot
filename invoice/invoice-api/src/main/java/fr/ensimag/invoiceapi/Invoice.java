package fr.ensimag.invoiceapi;

import java.util.*;

public class Invoice {

  private Long id;

  private String client;

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