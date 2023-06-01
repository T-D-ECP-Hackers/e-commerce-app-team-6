package org.global.ecp.hackathon.app.product.web;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.global.ecp.hackathon.app.product.ProductService;
import org.global.ecp.hackathon.app.product.model.Product;
import org.global.ecp.hackathon.app.product.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Product Endpoint", description = "All CRUD (create, retrieve, update and delete operations for products.)")
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable final Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody final ProductDto productDto) {

        return ResponseEntity.ok(productService.create(productDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductId(@PathVariable final Long id) {

        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
