package fr.ensimag.invoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import java.net.URI;
//import org.springframework.web.client.RestTemplate;

import fr.ensimag.product.Product;
import org.springframework.web.reactive.function.client.WebClient;



@RestController
public class InvoiceController {

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	InvoiceRepository invoiceRepository;


	@GetMapping("/port")
	public ResponseEntity<String> getPort() {
		
		return new ResponseEntity<>(serverPort, HttpStatus.OK);

	}

	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllProducts() {
		try {
			List<Invoice> invoices = new ArrayList<Invoice>();
			
			invoiceRepository.findAll().forEach(invoices::add); // on peut faire plus court non ?
			
			if (invoices.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(invoices, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/invoices/{id}")
	public ResponseEntity<Invoice> getInvoicetById(@PathVariable("id") long id) {
		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
		try {
			Invoice _invoice = invoiceRepository.save(invoice);
			return new ResponseEntity<>(_invoice, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			Invoice _invoice = invoiceData.get();
			_invoice.setClient(invoice.getClient());
			_invoice.setProductsOrder(invoice.getProductsOrder());
			return new ResponseEntity<>(invoiceRepository.save(_invoice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
		try {
			invoiceRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/invoices")
	public ResponseEntity<HttpStatus> deleteAllInvoices() {
		try {
			invoiceRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/invoices/{id}/count")
	public ResponseEntity<Long> getInvoiceCount(@PathVariable("id") long id) {

		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			return new ResponseEntity<>(invoiceData.get().NumberOfProduct(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/invoices/{id}/cost")
	public ResponseEntity<Float> getInvoice(@PathVariable("id") long id) {

		Optional<Invoice> invoiceData = invoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			Invoice invoice= invoiceData.get();

			List<String> idlist = new ArrayList<String>();

			for (Long key : invoice.productsOrder.keySet()) {
  				idlist.add(key.toString());
    		}
  			
			String url = "http://localhost:8080/products/list/"+String.join(",", idlist);


		WebClient.Builder builder = WebClient.builder();
		
		Product[] products = builder.
						build().
						get().
						uri(url).
						retrieve().
						bodyToMono(Product[].class).
						block();

			if(products!=null && products.length==invoice.productsOrder.size()){
				Float cost = 0f;
				for (Product product : products){
				cost+=product.getPrice()*invoice.productsOrder.get(product.getId());
				}
				return new ResponseEntity<>(cost, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}