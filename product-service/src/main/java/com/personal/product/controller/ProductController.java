package com.personal.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personal.product.domain.Product;
import com.personal.product.repository.ProductRepository;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> listProducts() {
		List<Product> result = productRepository.findAll();
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@RequestMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> detailProduct(@PathVariable("id") Long id) {
		return productRepository.findById(id);
	}
}
