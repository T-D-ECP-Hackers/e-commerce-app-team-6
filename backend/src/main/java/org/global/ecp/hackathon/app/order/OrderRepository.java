package org.global.ecp.hackathon.app.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.order.model.Order;
import org.global.ecp.hackathon.app.product.model.Product;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    // TODO - Task 9: implement the map storage and add method in this file
    //  Ensure that the map is called "orders"
    private final Map<UUID, Order> orders;

    public OrderRepository() {
        orders = new HashMap<>();
    }

    // TODO - Task 9: remove the return null
    //  uncomment the following line below once the orders map has been set up,
    //  this will be needed for Task 10
    public List<Order> getAll() {

        return orders.values().stream().toList();
    }

    void populate(UUID uuid, Order order) {
        orders.put(uuid, order);
    }

    // TODO - Task 12: implement the getById method here
}
