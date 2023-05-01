package org.global.ecp.hackathon.app.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getProductById(final Long id);

    Boolean existsByName(final String name);
}
