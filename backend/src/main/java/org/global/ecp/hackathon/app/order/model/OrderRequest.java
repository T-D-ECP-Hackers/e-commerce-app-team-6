package org.global.ecp.hackathon.app.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.global.ecp.hackathon.app.basket.model.Basket;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Basket basket;
    private double totalCost;
}
