package org.global.ecp.hackathon.app.product;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.exception.ProductAlreadyExistsException;
import org.global.ecp.hackathon.app.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {this.productRepository = productRepository;}

    public Product create(final ProductDto productDto) {

        final var productDtoName = productDto.getName();
        if (productRepository.existsByName(productDtoName)) {
            throw new ProductAlreadyExistsException("Product already exists with the same name: '" + productDtoName + "'");
        }
        final var product = createProduct(productDto);
        log.info("Created new product: '{}'", product);
        return productRepository.save(product);
    }

    public List<Product> getAll() {

        return productRepository.findAll();
    }

    public Product getById(final Long id) {

        final var optionalProduct = productRepository.getProductById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: '" + id + "'");
        }
        return optionalProduct.get();
    }

    public void deleteById(final Long id) {

        log.info("Deleting product with ID: '{}'", id);
        productRepository.deleteById(id);
    }

    private Product createProduct(final ProductDto productDto) {

        return Product.builder()
                      .name(productDto.getName())
                      .description(productDto.getDescription())
                      .price(productDto.getPrice())
                      .build();
    }
}
