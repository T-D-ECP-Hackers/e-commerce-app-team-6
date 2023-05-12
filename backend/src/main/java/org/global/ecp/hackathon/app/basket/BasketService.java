package org.global.ecp.hackathon.app.basket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.authentication.AuthService;
import org.global.ecp.hackathon.app.exception.ProductNotFoundException;
import org.global.ecp.hackathon.app.product.Product;
import org.global.ecp.hackathon.app.product.ProductRepository;
import org.global.ecp.hackathon.app.user.User;
import org.global.ecp.hackathon.app.user.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    public BasketService(final BasketRepository basketRepository,
                         final ProductRepository productRepository,
                         final AuthService authService,
                         final UserRepository userRepository) {

        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Transactional
    public Basket getBasketByUsername(final String username) {

        final var user = authService.getUser(username);
        final var optionalBasket = basketRepository.getBasketByUsername(user.getUsername());
        if (optionalBasket.isEmpty()) {
            log.info("Basket for username '" + username + "' not found, creating new basket");
            return basketRepository.save(createNewBasket(username));
        }
        return optionalBasket.get();
    }

    @Transactional
    public Basket addToBasket(final String username, final Long productId) {

        final var basket = getBasketByUsername(username);
        final var productById = productRepository.findById(productId);
        if (productById.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: '" + productId + "'");
        }
        final var optionalBasketProduct = getOptionalBasketProduct(basket, productById.get());

        if (optionalBasketProduct.isPresent()) {
            final var basketProduct = optionalBasketProduct.get();
            basketProduct.setCount(basketProduct.getCount() + 1);
        } else {
            basket.getBasketProducts().add(createNewBasketProduct(basket, productById.get()));
        }
        setTotalProductsInBasket(basket);
        return basketRepository.save(basket);
    }

    @Transactional
    public Basket removeFromBasket(final String username, final Long productId) {

        final var basket = getBasketByUsername(username);
        final var productById = productRepository.findById(productId);
        if (productById.isEmpty()) {
            throw new ProductNotFoundException("Product not found with id: '" + productId + "'");
        }
        final var optionalBasketProduct = getOptionalBasketProduct(basket, productById.get());

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

    @Transactional
    public Basket checkout(final String username) {

        final var basket = getBasketByUsername(username);
        basket.getBasketProducts().clear();
        setTotalProductsInBasket(basket);
        log.info("User checked out their basket: '{}'", username);
        return basketRepository.save(basket);
    }

    public void removeProductFromAllBaskets(final Long id) {

        final List<String> allUserNames = getAllUserNames();
        for (final var username : allUserNames) {
            final var optionalBasket = basketRepository.getBasketByUsername(username);
            optionalBasket.ifPresent(value -> value.getBasketProducts()
                                                   .removeIf(basketProduct -> basketProduct.getProduct()
                                                                                           .getId()
                                                                                           .equals(id)));
        }
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

    private List<String> getAllUserNames() {

        return userRepository.findAll()
                             .stream()
                             .map(User::getUsername)
                             .collect(Collectors.toList());
    }

    private BasketProduct createNewBasketProduct(final Basket basket, final Product productById) {

        return BasketProduct.builder().basket(basket).product(productById).count(1).build();
    }

    private void setTotalProductsInBasket(final Basket basket) {

        final var totalProducts = basket.getBasketProducts().stream().mapToInt(BasketProduct::getCount).sum();
        basket.setTotalProducts(totalProducts);
    }
}