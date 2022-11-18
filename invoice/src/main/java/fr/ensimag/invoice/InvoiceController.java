package fr.ensimag.invoice;

import java.util.ArrayList;
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

import fr.ensimag.product.Product;

@RestController

public class InvoiceController {

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	InvoiceRepository InvoiceRepository;


	@GetMapping("/port")
	public ResponseEntity<String> getPort() {
		
		return new ResponseEntity<>(serverPort, HttpStatus.OK);

	}

	@GetMapping("/invoices/{id}")
	public ResponseEntity<Invoice> getInvoicetById(@PathVariable("id") long id) {
		Optional<Invoice> invoiceData = InvoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
		try {
			Invoice _invoice = InvoiceRepository
					.save(new Invoice(invoice.getClient(), invoice.getOrder()));
			return new ResponseEntity<>(_invoice, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
		Optional<Invoice> invoiceData = InvoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			Invoice _invoice = invoiceData.get();
			_invoice.setClient(invoice.getClient());
			_invoice.setOrder(invoice.getOrder());
			return new ResponseEntity<>(InvoiceRepository.save(_invoice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {
		try {
			InvoiceRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/invoces")
	public ResponseEntity<HttpStatus> deleteAllInvoices() {
		try {
			InvoiceRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/invoices/{id}/count")
	public ResponseEntity<Long> getInvoiceById(@PathVariable("id") long id) {
		Optional<Invoice> invoiceData = InvoiceRepository.findById(id);

		if (invoiceData.isPresent()) {
			//List<Product> products = RestTemplate().getForObject(uri, Employee[].class);
			return new ResponseEntity<>(invoiceData.get().getNumberOfProduct(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}