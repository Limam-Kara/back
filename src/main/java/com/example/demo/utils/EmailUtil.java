package com.example.demo.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendResetPasswordEmail(String email, String token) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Réinitialiser votre mot de passe");

        String resetLink = "http://localhost:4200/Reset?token=" + token;

        // Construct the HTML email body with expiration notice
        String emailBody = "<div style=\"font-family: Plus Jakarta Sans, sans-serif; background-color: #ffffff; padding: 20px;\">\n" +
                "        <h1 style=\"color: #333; text-align: center; margin-bottom: 15px;\">Réinitialiser votre mot de passe</h1>\n" +
                "        <p style=\"text-align: center; margin-bottom: 30px;\">\n" +
                "          <a href=\"" + resetLink + "\" style=\"background-color: black; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 4px; display: inline-block;\">\n" +
                "            Réinitialiser le mot de passe\n" +
                "          </a>\n" +
                "        </p>\n" +
                "      </div>";

        mimeMessageHelper.setText(emailBody, true); // Set the HTML format to true

        javaMailSender.send(mimeMessage);
    }

}

