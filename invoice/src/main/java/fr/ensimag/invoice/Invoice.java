package fr.ensimag.invoice;

//import java.util.*;
import javax.persistence.*;

@Entity
public class Invoice {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String client;
  
  @Lob
  Long[][] productsOrder;
  //private Long[] products;
  //private Long[] quantity;

  protected Invoice() {}

  public Invoice(String client,  Long[][] productsOrder) {
    for (Long[] array : productsOrder){
      if (array == null || array.length != 2){
          throw new IllegalArgumentException("size should be 2 for all arrays");
      } 
  } 
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

  public Long[][] getProductsOrder() {
    return productsOrder;
  }

  public void setProductsOrder(Long[][] productsOrder) {
    for (Long[] array : productsOrder){
      if (array == null || array.length != 2){
          throw new IllegalArgumentException("size should be 2 for all arrays");
      } 
  } 
		this.productsOrder = productsOrder;
	}

  public Long getNumberOfProduct() {
    Long sum=0L;

    for (Long[] product : productsOrder) {
      sum += product[1];
  }
    return sum;
  }

}