package com.microservices.mentoring.mailer.service;

import com.microservices.mentoring.mailer.domain.EmailType;
import com.microservices.mentoring.mailer.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MailRepository mailRepository;

    public void send(String toAddress, String messageType) {
        EmailType type = EmailType.valueOf(messageType);
        mailRepository.sendEmail(toAddress, type);
    }
}
