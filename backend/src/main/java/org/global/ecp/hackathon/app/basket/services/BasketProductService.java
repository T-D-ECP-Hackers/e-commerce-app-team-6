package org.global.ecp.hackathon.app.basket.services;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.basket.model.Basket;
import org.global.ecp.hackathon.app.basket.model.BasketProduct;
import org.global.ecp.hackathon.app.product.ProductService;
import org.global.ecp.hackathon.app.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasketProductService {

    @Autowired
    private ProductService productService;

    public BasketProduct createBasketProduct(final Basket basket,
                                             final Long productId) {

        Product product = productService.getById(productId);
        if (product == null) {
            log.error("Product does not exist");
        }
        BasketProduct basketProduct = doesProductAlreadyExistInBasket(basket, product);
        if (basketProduct == null) {
            basketProduct = new BasketProduct(product, 0);
        }
        return basketProduct;
    }

    public BasketProduct doesProductAlreadyExistInBasket(final Basket basket,
                                                         final Product product) {

        Optional<BasketProduct> optionalBasketProduct = basket.getBasketProducts()
                                                              .stream()
                                                              .filter(basketProduct -> basketProduct.getProduct()
                                                                                                    .getId() == product.getId())
                                                              .findFirst();

        if (optionalBasketProduct.isEmpty()) {
            return null;
        }
        return optionalBasketProduct.get();
    }

}
