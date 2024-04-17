package org.example.contact.service;

import jakarta.mail.MessagingException;
import org.example.contact.dto.ContactDTO;

public interface ContactService {
    void sendEmail(ContactDTO data, String from)throws MessagingException;
}
