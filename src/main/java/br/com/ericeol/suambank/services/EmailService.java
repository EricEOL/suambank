package br.com.ericeol.suambank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(String senderAccountEmail, String destinationAccountEmail, String textMessage) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("quillbolteol333@hotmail.com");
        msg.setTo(senderAccountEmail, destinationAccountEmail);
        msg.setSubject("SUAMBANK - Transação realizada");
        msg.setText(textMessage);

        javaMailSender.send(msg);
    }

    void sendHTMLEmail(String senderAccountEmail, String destinationAccountEmail, String value, String transaction) throws MessagingException {
        String[] emailsTo = {senderAccountEmail, destinationAccountEmail};
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail);
        helper.setFrom("quillbolteol333@hotmail.com");
        helper.setTo(emailsTo);
        helper.setSubject("SUAMBANK - Transação realizada");
        helper.setText(
                "<div style=\"background-color:#e5e5e5\" >" +
                        "<p align=\"center\">\n" +
                        "<img src=\"https://raw.githubusercontent.com/EricEOL/suambank/main/readme/suambank.png\" alt=\"\" srcset=\"\">\n" +
                        "</p>" +
                        "<h2 align=\"center\" style=\"color:#1995a7\">Transação realizada</h2>" +
                        "<h3 align=\"center\">" + transaction + " no valor de " + value + "</h3>" +
                        "<p align=\"center\">Conte sempre com o SUAMBANK</p>" +
                        "</div>",
                true);

        javaMailSender.send(mail);
    }
}
