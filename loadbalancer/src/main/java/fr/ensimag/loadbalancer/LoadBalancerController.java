package fr.ensimag.loadbalancer;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
//import java.net.URI;
//import org.springframework.web.client.RestTemplate;

import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
@RestController
public class LoadBalancerController {


	@Autowired
	private WebClient.Builder loadBalancedWebClientBuilder;



    @GetMapping("/port")
    Mono<String> getPort() {
        return loadBalancedWebClientBuilder
            .build().get().uri("http://invoice/port")
            .retrieve().bodyToMono(String.class);
      }
    

	// @GetMapping("/invoices")
	// public ResponseEntity<List<Invoice>> getAllProducts() {
		
	// }

	// @GetMapping("/invoices/{id}")
	// public ResponseEntity<Invoice> getInvoicetById(@PathVariable("id") long id) {

	// }

	// @PostMapping("/invoices")
	// public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {

	// }

	// @PutMapping("/invoices/{id}")
	// public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {

	// }

	// @DeleteMapping("/invoices/{id}")
	// public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") long id) {

	// }

	// @DeleteMapping("/invoices")
	// public ResponseEntity<HttpStatus> deleteAllInvoices() {
	
	// }

	// @GetMapping("/invoices/{id}/count")
	// public ResponseEntity<Long> getInvoiceCount(@PathVariable("id") long id) {

    // }


	// @GetMapping("/invoices/{id}/cost")
	// public ResponseEntity<Float> getInvoice(@PathVariable("id") long id) {


	// }

}