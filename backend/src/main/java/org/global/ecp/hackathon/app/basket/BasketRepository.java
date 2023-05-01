package org.global.ecp.hackathon.app.basket;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> getBasketByUsername(final String username);

}
