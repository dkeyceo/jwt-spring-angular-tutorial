package com.dkey.jwt.spring.backend.tutorial.controller;

import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.dto.ProductDto;
import com.dkey.jwt.spring.backend.tutorial.entity.Product;
import com.dkey.jwt.spring.backend.tutorial.service.ProductService;

import io.micrometer.common.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation("Get list of products")
    @GetMapping("/list")
    public ResponseEntity<List<Product>> list(){
        return ResponseEntity.ok(productService.list());
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){
        return ResponseEntity.ok(productService.getOne(id));
    }

    @ApiIgnore
    @GetMapping("/detailsname/{name}")
    public ResponseEntity<Product> getByName(@PathVariable("name") String name){
    	return ResponseEntity.ok(productService.getByName(name));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Message> create(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Message> update(@PathVariable("id")int id, @Valid @RequestBody ProductDto productDto){        
        return ResponseEntity.ok(productService.update(id, productDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id")int id){
        return ResponseEntity.ok(productService.delete(id));
    }
}
