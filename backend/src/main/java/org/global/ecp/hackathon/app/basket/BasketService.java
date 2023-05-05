package org.global.ecp.hackathon.app.basket;

import java.util.ArrayList;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.authentication.AuthService;
import org.global.ecp.hackathon.app.product.Product;
import org.global.ecp.hackathon.app.product.ProductService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;
    private final AuthService authService;

    public BasketService(final BasketRepository basketRepository,
                         final ProductService productService,
                         final AuthService authService) {

        this.basketRepository = basketRepository;
        this.productService = productService;
        this.authService = authService;
    }

    public Basket getBasketByUsername(final String username) {

        final var user = authService.getUser(username);
        final var optionalBasket = basketRepository.getBasketByUsername(user.getUsername());
        if (optionalBasket.isEmpty()) {
            log.info("Basket for username '" + username + "' not found, creating new basket");
            return basketRepository.save(createNewBasket(username));
        }
        return optionalBasket.get();
    }

    public Basket addToBasket(final String username, final Long productId) {

        final var basket = getBasketByUsername(username);
        final var productById = productService.getById(productId);
        final var optionalBasketProduct = getOptionalBasketProduct(basket, productById);

        if (optionalBasketProduct.isPresent()) {
            final var basketProduct = optionalBasketProduct.get();
            basketProduct.setCount(basketProduct.getCount() + 1);
        } else {
            basket.getBasketProducts().add(createNewBasketProduct(basket, productById));
        }
        setTotalProductsInBasket(basket);
        return basketRepository.save(basket);
    }

    public Basket removeFromBasket(final String username, final Long productId) {

        final var basket = getBasketByUsername(username);
        final var productById = productService.getById(productId);
        final var optionalBasketProduct = getOptionalBasketProduct(basket, productById);

        if (optionalBasketProduct.isPresent()) {
            final var basketProduct = optionalBasketProduct.get();
            if (basketProduct.getCount() == 1) {
                basket.getBasketProducts().remove(basketProduct);
            } else {
                basketProduct.setCount(basketProduct.getCount() - 1);
            }
        }
        setTotalProductsInBasket(basket);
        return basketRepository.save(basket);
    }

    public Basket checkout(final String username) {

        final var basket = getBasketByUsername(username);
        basket.getBasketProducts().clear();
        setTotalProductsInBasket(basket);
        return basketRepository.save(basket);
    }

    private Basket createNewBasket(final String username) {

        return Basket.builder().username(username).basketProducts(new ArrayList<>()).totalProducts(0).build();
    }

    private Optional<BasketProduct> getOptionalBasketProduct(final Basket basket, final Product productById) {

        return basket.getBasketProducts()
                     .stream()
                     .filter(basketProduct -> basketProduct.getProduct()
                                                           .equals(productById))
                     .findFirst();
    }

    private BasketProduct createNewBasketProduct(final Basket basket, final Product productById) {

        return BasketProduct.builder().basket(basket).product(productById).count(1).build();
    }

    private void setTotalProductsInBasket(final Basket basket) {

        final var totalProducts = basket.getBasketProducts().stream().mapToInt(BasketProduct::getCount).sum();
        basket.setTotalProducts(totalProducts);
    }
}