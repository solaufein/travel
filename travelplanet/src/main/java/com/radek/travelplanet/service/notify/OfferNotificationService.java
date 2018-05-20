package com.radek.travelplanet.service.notify;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class OfferNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    public OfferNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onOfferChange() {
        //todo: implement
        SimpleMailMessage message = createMessage("", "", "");
        mailSender.send(message);   //todo: ex handling
    }

    private SimpleMailMessage createMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        return message;
    }
}
