package com.microservices.mentoring.mailer.controller;

import com.microservices.mentoring.mailer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/notification", consumes = {"application/json"})
    public void sendEmail(@RequestBody Map<String, String> params) {
        mailService.send(params.get("address"), params.get("emailType"));
    }
}
