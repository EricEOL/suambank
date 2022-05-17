package br.com.ericeol.suambank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(String senderAccountEmail, String destinationAccountEmail, String textMessage) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("quillbolteol333@hotmail.com");
        msg.setTo(senderAccountEmail, destinationAccountEmail);
        msg.setSubject("SUAMBANK - Transação realizada em sua conta");
        msg.setText(textMessage);

        javaMailSender.send(msg);
    }
}
