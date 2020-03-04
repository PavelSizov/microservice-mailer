package com.microservices.mentoring.mailer.repository;

import com.microservices.mentoring.mailer.domain.EmailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MailRepository {

    @Autowired
    private AmazonSESRepository amazonSESRepository;

    public void sendEmail(String toAddress, EmailType type) {
        amazonSESRepository.sendEmail(toAddress, type);
    }
}
