package com.dkey.jwt.spring.backend.tutorial.service;

import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.dto.ProductDto;
import com.dkey.jwt.spring.backend.tutorial.entity.Product;
import com.dkey.jwt.spring.backend.tutorial.exceptions.CustomException;
import com.dkey.jwt.spring.backend.tutorial.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Product getOne(int id){
        return productRepository.findById(id)
        		.orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "product not found"));
    }

    public Product getByName(String name){
    	return productRepository.findByName(name)
        		.orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "product not found"));
    }

    public Message save(ProductDto productDto){
        if(productRepository.existsByName(productDto.getName()))
        	throw new CustomException(HttpStatus.BAD_REQUEST, "name is exists");
    	Product product = new Product(productDto.getName(), productDto.getPrice());
        productRepository.save(product);
        return new Message(product.getName() + " is created");
    }
    
    public Message update(int id, ProductDto productDto) {
    	if(!productRepository.existsById(id))
    		throw new CustomException(HttpStatus.NOT_FOUND, "Product not exists");
    	Optional<Product> productOpt = productRepository.findByName(productDto.getName());
    	if(productOpt.isPresent() && productOpt.get().getId() != id)
    		throw new CustomException(HttpStatus.BAD_REQUEST, "Name is exists");
    	Product product = getOne(id);
    	
    	product.setName(productDto.getName());
    	product.setPrice(productDto.getPrice());
    	productRepository.save(product);
    	return new Message(product.getName() + " product is updated");
    }

    public Message delete(int id){
        Product product = getOne(id);
        productRepository.delete(product);
        
        return new Message(product.getName() + " is deleted");
    }

    public boolean existsById(int id){
        return productRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return productRepository.existsByName(name);
    }
}
