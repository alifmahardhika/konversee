package com.development.konversee.service;

import org.springframework.mail.SimpleMailMessage;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
    CompletableFuture asyncMailSender(String to, String subject, String text);
//    void sendSimpleMessageUsingTemplate(String to,
//                                        String subject,
//                                        SimpleMailMessage template,
//                                        String ...templateArgs);
//    void sendMessageWithAttachment(String to,
//                                   String subject,
//                                   String text,
//                                   String pathToAttachment);
}