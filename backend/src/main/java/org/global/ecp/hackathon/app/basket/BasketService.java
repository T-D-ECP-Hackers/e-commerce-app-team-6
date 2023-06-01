package org.global.ecp.hackathon.app.basket;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.basket.model.Basket;
import org.global.ecp.hackathon.app.basket.model.BasketProduct;
import org.global.ecp.hackathon.app.basket.services.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketProductService basketProductService;

    public Basket getBasket() {

        return basketRepository.get();
    }

    // TODO - Task 3: add basketProduct to basket using the basketRepository
    // Hint: basketRepository.someMethod(variableToPassIntoMethod);
    public Basket addToBasket(final Long productId) {

        BasketProduct basketProduct = basketProductService.createBasketProduct(getBasket(), productId);
        basketProduct.increaseQuantity();
        return null;
    }

    // TODO - Task 5: remove basketProduct from the basket using the basketRepository
    public Basket removeFromBasket(final Long productId) {

        BasketProduct basketProduct = basketProductService.createBasketProduct(getBasket(), productId);
        basketProduct.decreaseQuantity();
        return null;
    }

    // TODO - Task 8: implement checkout method
    public Basket checkout() {

        return null;
    }
}