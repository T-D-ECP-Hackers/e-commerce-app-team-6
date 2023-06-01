package org.global.ecp.hackathon.app.order;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.email.EmailService;
import org.global.ecp.hackathon.app.order.model.Order;
import org.global.ecp.hackathon.app.order.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    // TODO - Task 9: implement this method
    public UUID createOrder(final OrderRequest orderRequest) {

        final UUID orderId = UUID.randomUUID();
        final LocalDateTime orderedTime = now();
        final double totalCost = orderRequest.getTotalCost();
        final var orderedProducts = orderRequest.getBasket().getBasketProducts();

        if (orderedProducts != null) {
            final Order userOrder = new Order(orderId, orderedTime, totalCost, orderedProducts );
            orderRepository.populate(orderId, userOrder);
            log.info("Order created: {}", userOrder);
            return orderId;
        }
        return null;
    }

    public List<Order> getAllOrders() {

        return orderRepository.getAll();
    }

    // TODO - Task 12: create a complete order method here
    public void complete(UUID orderId) {
        if (orderId != null ) {
            orderRepository.completeOrder(orderId);
            sendEmailNotification(orderId);
        }
    }
    public void sendEmailNotification(UUID orderId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("task@submission.com");
        message.setSubject("Order placed");
        String body = "Order: " + orderId + "on " + now();
        message.setText(body);

        mailSender.send(message);
    }
}

