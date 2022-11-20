package fr.ensimag.invoice;

import java.util.*;

public class InvoiceApi {

  private String client;
  
  Long[][] productsOrder;
  //private Long[] products;
  //private Long[] quantity;

  protected InvoiceApi() {}

  public InvoiceApi(String client,  Long[][] productsOrder) {

    if (productsOrder == null || productsOrder.length != 2 || productsOrder[0] == null ||productsOrder[1] == null|| productsOrder[0].length!=productsOrder[1].length){
        throw new IllegalArgumentException("size should be 2 and not null");
    } 
    this.client = client;
    this.productsOrder = productsOrder;

    }

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
		this.client = client;
	}

  public Long[][] getProductsOrder() {
    return productsOrder;
  }


  public void setProductsOrder(Long[][] productsOrder) {
    if (productsOrder == null || productsOrder.length != 2 || productsOrder[0] == null ||productsOrder[1] == null|| productsOrder[0].length!=productsOrder[1].length){
      throw new IllegalArgumentException("size should be 2 and not null");
  } 
  this.productsOrder = productsOrder;
  }

  public Invoice toInvoice(){

    Map<Long, Long> productsOrder = new HashMap<Long, Long>();

    for (int i = 0; i < this.productsOrder[0].length; i++) {
      productsOrder.put(this.productsOrder[0][i], this.productsOrder[1][i]);
  }


    return new Invoice(this.client,productsOrder);
    
	}
}

