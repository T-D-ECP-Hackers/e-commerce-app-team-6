package org.global.ecp.hackathon.app.basket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.global.ecp.hackathon.app.product.model.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketProduct {

    private Product product;
    private Integer quantity;

    public void increaseQuantity() {

        quantity++;
    }

    public void decreaseQuantity() {

        if (quantity != 0) {
            quantity--;
        }
    }
}