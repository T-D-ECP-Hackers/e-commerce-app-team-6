package org.global.ecp.hackathon.app.product;

import java.util.List;

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

        // what happens if product name already exists
        return null;
    }

    public void deleteById(final Long id) {

        productRepository.deleteById(id);
    }

}
