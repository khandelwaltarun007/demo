package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Product;

public interface IProductService {
	public Product createProduct(Product product);
	public List<Product> getProducts();
	public Optional<Product> getProductById(Long id);
	public Product updateProduct(Product product);
	public void deleteProduct(Long id);
}
