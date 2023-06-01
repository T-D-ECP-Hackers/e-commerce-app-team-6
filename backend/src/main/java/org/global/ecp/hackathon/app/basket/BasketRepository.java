package org.global.ecp.hackathon.app.basket;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.basket.model.Basket;
import org.global.ecp.hackathon.app.basket.model.BasketProduct;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BasketRepository {

    private final Basket basket;

    public BasketRepository() {

        basket = new Basket(new ArrayList<>(), 0);
    }

    public Basket get() {

        return basket;
    }

    public Basket add(final BasketProduct basketProduct) {

        List<BasketProduct> basketProducts = basket.getBasketProducts();
        if (!basketProducts.contains(basketProduct)) {
            basketProducts.add(basketProduct);
        }
        adjustTotalProducts(basket);
        return basket;
    }

    public Basket remove(final BasketProduct basketProduct) {

        if (basketProduct.getQuantity() == 0) {
            basket.getBasketProducts().remove(basketProduct);
        }
        adjustTotalProducts(basket);
        return basket;
    }

    public Basket clear() {

        basket.getBasketProducts().clear();
        basket.resetTotalProducts();
        return basket;
    }

    private void adjustTotalProducts(final Basket basket) {

        int totalProducts = basket.getBasketProducts().stream().mapToInt(BasketProduct::getQuantity).sum();
        basket.setTotalProducts(totalProducts);
    }
}
