package com.novi.ManageMe.services.user;

import com.novi.ManageMe.models.user.password.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Service
public class EmailService {

    @Qualifier("getJavaMailSender") // using qualifier to use the JavaMailSender created in ManageMeApplication
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    // send email
    public void sendEmail(Mail mail) {
        try {

            // create Multipurpose Internet Mail Extensions message
            MimeMessage message = emailSender.createMimeMessage();

            // create Multipurpose Internet Mail Extensions message helper
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, // MIME message contains a mix of different data types
                    StandardCharsets.UTF_8.name()); // set charset

            // create contect
            Context context = new Context();

            // set variable resetUrl in context
            context.setVariable("resetUrl", mail.getModel().get("resetUrl"));

            // using Thymeleaf's SpringTemplateEngine to create a HTML e-mail
            String html = templateEngine.process("email/email-template", context);

            // set helper
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            // send mail
            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
