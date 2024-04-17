package org.example.contact.service.serviceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.contact.dto.ContactDTO;
import org.example.contact.entity.Contact;
import org.example.contact.repository.ContactRepository;
import org.example.contact.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    ModelMapper modelMapper;
    private static final String ADMIN_EMAIL = "laghfirimariam@gmail.com";

    @Override
    public void sendEmail(ContactDTO data, String from) throws MessagingException {
        String subject = data.getSubject();
        String body = "Name: " + data.getName() + "\nEmail: " + data.getEmail() + "\nSubject: " + data.getSubject() + "\nMessage: " + data.getMessage();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(ADMIN_EMAIL);
        helper.setSubject(subject);
        helper.setText(body, true);

        SimpleMailMessage message1 = new SimpleMailMessage();
        message1.setTo(ADMIN_EMAIL);
        message1.setSubject(subject);
        message1.setText(body);

        mailSender.send(message1);

        mailSender.send(message1);
        contactRepository.save(modelMapper.map(data, Contact.class));

    }
}
