package org.product_delivery_backend.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.product_delivery_backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<org.product_delivery_backend.dto.productDto.AllProductResponseDto>> findAll() {
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<org.product_delivery_backend.dto.productDto.AllProductResponseDto>> findAllPage(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(productService.findAllProductPage(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<org.product_delivery_backend.dto.productDto.ProductResponseDto> addProduct(@RequestBody org.product_delivery_backend.dto.productDto.ProductRequestDto productRequestDto) {
        org.product_delivery_backend.dto.productDto.ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<org.product_delivery_backend.dto.productDto.ProductResponseDto> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<org.product_delivery_backend.dto.productDto.ProductResponseDto> findByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);

    }
}
