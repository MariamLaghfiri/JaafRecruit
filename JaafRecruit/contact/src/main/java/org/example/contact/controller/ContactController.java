package org.example.contact.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.example.contact.dto.ContactDTO;
import org.example.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping("/send-email")
    public ResponseEntity<Boolean> sendEmail(@Valid @RequestBody ContactDTO data) {
        try {
            contactService.sendEmail(data,null);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (MessagingException e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}