package com.microservices.mentoring.mailer.domain;

import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {

    private static final String SUCCESS_SUBJECT = "Your order is completed";
    private static final String PROMOCODE_FAILURE_SUBJECT = "Your order is completed without promocode";

    // The HTML body for the email.
    private static final String SUCCESS_HTMLBODY =
            "<h1>Thanks for your purchase!</h1>" + "<p>Your order has been fully completed.</p>";
    private static final String PROMOCODE_FAILURE_HTMLBODY = "<h1>Thanks for your purchase!</h1>" +
            "<p>Your order has been completed but provided promocode was not applied.</p>" +
            "<p>Please contact our support to apply it.</p>";

    public static Message createMessage(EmailType type) {
        if (EmailType.SUCCESS == type) {
            return createSuccessMessage();
        }
        if (EmailType.PROMOCODE_FAILURE == type){
            return createPromocodeFailureMessage();
        }

        throw new IllegalArgumentException(String.valueOf(type));
    }

    private static Message createSuccessMessage() {
        return new Message()
                .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(SUCCESS_HTMLBODY)))
                .withSubject(new Content().withCharset("UTF-8").withData(SUCCESS_SUBJECT));
    }

    private static Message createPromocodeFailureMessage() {
        return new Message()
                .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(PROMOCODE_FAILURE_HTMLBODY)))
                .withSubject(new Content().withCharset("UTF-8").withData(PROMOCODE_FAILURE_SUBJECT));
    }
}
