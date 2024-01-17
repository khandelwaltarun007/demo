package com.example.demo.resource;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.IProductService;

@RestController
public class ProductResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);

	@Autowired
	private IProductService productService;

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		LOGGER.info("Product has been saved with product id : "+createdProduct.getId());
		return new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProducts() {
		return ResponseEntity.ok(productService.getProducts());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
		return productService.getProductById(id).map(ResponseEntity::ok)
				.orElseThrow(() -> new EntityNotFoundException("Product does not exist."));
	}
	
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		LOGGER.info("Product details of id : "+product.getId()+" has been updated.");
		return ResponseEntity.ok(productService.updateProduct(product));
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		if (Objects.isNull(id))
			throw new IllegalArgumentException("Product Id is mandatory.");
		else {
			productService.deleteProduct(id);
			LOGGER.info("Product :" + id + " has been deleted.");
			return ResponseEntity.ok("Product :" + id + " has been deleted.");
		}
	}
}
