package fr.ensimag.loadbalancer;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
// import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
//import java.net.URI;
//import org.springframework.web.client.RestTemplate;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;

import fr.ensimag.invoice.Invoice;


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

	@GetMapping("/invoices/{id}/cost")
	public Mono<ResponseEntity<Float>> getCost(@PathVariable("id") long id) {

		return loadBalancedWebClientBuilder
		.build().get().uri("http://invoice/invoices/{id}/cost",id)
		.accept(MediaType.ALL).retrieve().toEntity(Float.class);
	}
    

	@GetMapping("/invoices")
	public Mono<ResponseEntity<List<Invoice>>> getAllProducts() {

		return loadBalancedWebClientBuilder
		.build().get().uri("http://invoice/invoices")
		.accept(MediaType.ALL).retrieve().toEntityList(Invoice.class);
		
	}

	@GetMapping("/invoices/{id}")
	public Mono<ResponseEntity<Invoice>> getInvoicetById(@PathVariable("id") long id) {

		return loadBalancedWebClientBuilder
		.build().get().uri("http://invoice/invoices/{id}",id)
		.accept(MediaType.ALL).retrieve().toEntity(Invoice.class);

	}

	@GetMapping("/invoices/{id}/count")
	public Mono<ResponseEntity<Long>> getInvoiceCount(@PathVariable("id") long id) {

		return loadBalancedWebClientBuilder
		.build().get().uri("http://invoice/invoices/{id}/count",id)
		.accept(MediaType.ALL).retrieve().toEntity(Long.class);

    }

	@PostMapping("/invoices")
	public Mono<ResponseEntity<Invoice>> createInvoice(@RequestBody Invoice invoice) {

		return loadBalancedWebClientBuilder
		.build().post().uri("http://invoice/invoices/")
		.body(BodyInserters.fromValue(invoice)).retrieve().toEntity(Invoice.class);

	}

	@PutMapping("/invoices/{id}")
	public Mono<ResponseEntity<Invoice>> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {

		return loadBalancedWebClientBuilder
		.build().put().uri("http://invoice/invoices/{id}",id)
		.body(BodyInserters.fromValue(invoice)).retrieve().toEntity(Invoice.class);
	}

	@DeleteMapping("/invoices/{id}")
	public Mono<ResponseEntity<HttpStatus>> deleteInvoice(@PathVariable("id") long id) {
		return loadBalancedWebClientBuilder
		.build().delete().uri("http://invoice/invoices/{id}",id)
		.accept(MediaType.ALL).retrieve().toEntity(HttpStatus.class);
	}

	@DeleteMapping("/invoices")
	public Mono<ResponseEntity<HttpStatus>> deleteAllInvoices() {
	
		return loadBalancedWebClientBuilder
		.build().delete().uri("http://invoice/invoices")
		.accept(MediaType.ALL).retrieve().toEntity(HttpStatus.class);
	}

}