package org.global.ecp.hackathon.app.basket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@CrossOrigin(origins = "http://localhost:3000")
public class BasketController {

    private final BasketService basketService;

    public BasketController(final BasketService basketService) {

        this.basketService = basketService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Basket> getForUser(@PathVariable final String username) {

        return ResponseEntity.ok(basketService.getBasketByUsername(username));
    }

    @PostMapping("/{username}")
    public ResponseEntity<Basket> addProduct(@PathVariable final String username,
                                             @RequestParam final Long productId) {

        return ResponseEntity.ok(basketService.addToBasket(username, productId));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Basket> removeProduct(@PathVariable final String username,
                                                @RequestParam final Long productId) {

        return ResponseEntity.ok(basketService.removeFromBasket(username, productId));
    }

    @PostMapping("/checkout/{username}")
    public ResponseEntity<Basket> checkout(@PathVariable final String username) {
        return ResponseEntity.ok(basketService.checkout(username));
    }

}