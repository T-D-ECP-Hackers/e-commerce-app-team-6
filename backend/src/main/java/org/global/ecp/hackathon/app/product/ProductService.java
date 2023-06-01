package org.global.ecp.hackathon.app.product;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.product.model.Product;
import org.global.ecp.hackathon.app.product.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {

        return productRepository.getAll();
    }

    public Product getById(final Long id) {

        return productRepository.getById(id);
    }

    public Product create(final ProductDto productDto) {

        final var existingProducts = getAll();
        for (var product : existingProducts) {
            if(product.getName() == productDto.getName()) {
                log.info("Product exists");
                return product;
            }
        }
        long largestId = 0;
        for (var product : existingProducts) {
            if(product.getId() > largestId) {
                largestId = product.getId();
            }
        }
        var generatedProduct = new Product(largestId +1, productDto.getName(), productDto.getDescription(), productDto.getPrice());
        productRepository.add(generatedProduct);
        return generatedProduct;
    }

    public void deleteById(final Long id) {

        productRepository.deleteById(id);
    }

}
