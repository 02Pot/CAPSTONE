package com.capstone1.automatedpayroll.controller;

import com.capstone1.automatedpayroll.model.MailModel;
import com.capstone1.automatedpayroll.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send/{employeeId}")
    public String sendEmail(@RequestBody MailModel mailModel, @PathVariable Long employeeId) throws MessagingException {
        mailService.sendMail(mailModel,employeeId);
        return "Success";
    }

}