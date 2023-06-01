package org.global.ecp.hackathon.app.product.model;

import lombok.Data;

@Data
public class Product {

    private long id;
    private String name;
    private String description;
    private double price;

    public Product(final long id, final String name, final String description, final double price) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
