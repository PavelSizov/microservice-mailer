package com.microservices.mentoring.mailer.repository;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.microservices.mentoring.mailer.domain.EmailType;
import com.microservices.mentoring.mailer.domain.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class AmazonSESRepository {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Autowired
    private MessageFactory messageFactory;

    private static final String FROM = "pavelsizov13@gmail.com";

    private AmazonSimpleEmailService client;


    @PostConstruct
    private void init() {

        client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(region).build();
    }

    public void sendEmail(String toAddress, EmailType type) {

        Message message = MessageFactory.createMessage(type);
        SendEmailRequest request = createRequest(toAddress, message);
        client.sendEmail(request);

    }

    private SendEmailRequest createRequest(String toAddress, Message message) {
        return new SendEmailRequest().withDestination(new Destination().withToAddresses(toAddress)).withMessage(message)
                .withSource(FROM);
    }
}
