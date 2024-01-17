package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {

		return productRepository.save(product);
	}

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	@Cacheable(value = "products", key = "#id")
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	@CachePut(value = "products", key = "#product.id")
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	@CacheEvict(value = "products", key = "#id")
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
