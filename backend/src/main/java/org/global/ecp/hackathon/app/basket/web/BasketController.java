package org.global.ecp.hackathon.app.basket.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.global.ecp.hackathon.app.basket.BasketService;
import org.global.ecp.hackathon.app.basket.model.Basket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Basket Endpoint", description = "Get a basket for a user, add and remove products from the basket and checkout.")
@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:3000")
public class BasketController {

    private final BasketService basketService;

    public BasketController(final BasketService basketService) {

        this.basketService = basketService;
    }

    @GetMapping
    public ResponseEntity<Basket> getBasket() {

        return ResponseEntity.ok(basketService.getBasket());
    }

    @PostMapping
    public ResponseEntity<Basket> addProduct(@RequestParam final Long productId) {

        return ResponseEntity.ok(basketService.addToBasket(productId));
    }

    @DeleteMapping
    public ResponseEntity<Basket> removeProduct(@RequestParam final Long productId) {

        return ResponseEntity.ok(basketService.removeFromBasket(productId));
    }

    @PostMapping("/checkout")
    public ResponseEntity<Basket> checkout() {

        return ResponseEntity.ok(basketService.checkout());
    }

}