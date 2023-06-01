package org.global.ecp.hackathon.app.email;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    // TODO - Task 11: send a simple mail message to the email server
    public void sendEmail(final Order newOrder) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
