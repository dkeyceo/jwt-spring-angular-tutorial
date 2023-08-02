package com.dkey.jwt.spring.backend.tutorial.controller;

import com.dkey.jwt.spring.backend.tutorial.dto.Message;
import com.dkey.jwt.spring.backend.tutorial.dto.ProductDto;
import com.dkey.jwt.spring.backend.tutorial.entity.Product;
import com.dkey.jwt.spring.backend.tutorial.service.ProductService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/lista")
    public ResponseEntity<List<Product>> list(){
        List<Product> list = productService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Product producto = productService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/detailname/{name}")
    public ResponseEntity<Product> getByName(@PathVariable("name") String name){
        if(!productService.existsByName(name))
            return new ResponseEntity(new Message("no exists"), HttpStatus.NOT_FOUND);
        Product producto = productService.getByName(name).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
       if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("name is required"), HttpStatus.BAD_REQUEST);
        if(productDto.getPrice()==null || productDto.getPrice()<0 )
            return new ResponseEntity(new Message("price must be greater than 0"), HttpStatus.BAD_REQUEST);
        if(productService.existsByName(productDto.getName()))
            return new ResponseEntity(new Message("name exists"), HttpStatus.BAD_REQUEST);
        Product producto = new Product(productDto.getName(), productDto.getPrice());
        productService.save(producto);
        return new ResponseEntity(new Message("product created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ProductDto productDto){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("no exists"), HttpStatus.NOT_FOUND);
        if(productService.existsByName(productDto.getName()) && productService.getByName(productDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("name exists"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(productDto.getName()))
            return new ResponseEntity(new Message("name is required"), HttpStatus.BAD_REQUEST);
        if(productDto.getPrice()==null || productDto.getPrice()<0 )
            return new ResponseEntity(new Message("price must be greater than 0"), HttpStatus.BAD_REQUEST);

        Product product = productService.getOne(id).get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productService.save(product);
        return new ResponseEntity(new Message("product is updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!productService.existsById(id))
            return new ResponseEntity(new Message("no exists"), HttpStatus.NOT_FOUND);
        productService.delete(id);
        return new ResponseEntity(new Message("product is deleted"), HttpStatus.OK);
    }


}
