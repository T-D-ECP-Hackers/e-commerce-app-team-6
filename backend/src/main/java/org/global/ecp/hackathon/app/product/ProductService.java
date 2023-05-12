package org.global.ecp.hackathon.app.product;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.basket.BasketProduct;
import org.global.ecp.hackathon.app.basket.BasketProductRepository;
import org.global.ecp.hackathon.app.basket.BasketService;
import org.global.ecp.hackathon.app.exception.ProductAlreadyExistsException;
import org.global.ecp.hackathon.app.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BasketProductRepository basketProductRepository;
    private final BasketService basketService;


    public ProductService(final ProductRepository productRepository,
                          final BasketProductRepository basketProductRepository,
                          final BasketService basketService) {

        this.productRepository = productRepository;
        this.basketProductRepository = basketProductRepository;
        this.basketService = basketService;
    }

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
        final var optionalBasketProduct = getOptionalBasketProductByProductId(id);
        if (optionalBasketProduct.isPresent()) {
            basketService.removeProductFromAllBaskets(id);
            basketProductRepository.deleteById(optionalBasketProduct.get().getId());
            productRepository.deleteById(id);
        }
    }

    private Product createProduct(final ProductDto productDto) {

        return Product.builder()
                      .name(productDto.getName())
                      .description(productDto.getDescription())
                      .price(productDto.getPrice())
                      .build();
    }

    private Optional<BasketProduct> getOptionalBasketProductByProductId(final Long id) {

        final var basketProducts = basketProductRepository.findAll();
        return basketProducts.stream()
                             .filter(basketProduct -> basketProduct.getProduct()
                                                                   .getId()
                                                                   .equals(id))
                             .findFirst();
    }
}
