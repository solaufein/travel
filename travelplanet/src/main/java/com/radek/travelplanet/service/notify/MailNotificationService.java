package com.radek.travelplanet.service.notify;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    public MailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void onOfferChange(String to, String subject, String text) {
        SimpleMailMessage message = createMessage(to, subject, text);
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
