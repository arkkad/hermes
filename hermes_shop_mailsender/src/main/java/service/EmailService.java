package service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
public class EmailService implements IEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Value("${verify.email.path}")
    private String verifyEmailPath;

    @Value("${from.email}")
    private String from;

    @Value("${from.name}")
    private String name;

    @Value("${admin.email}")
    private String adminEmail;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmailVerificationCode(Map<String, String> message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

//            String url = contextPath + verifyEmailPath + "?id=" + message.get("userId") + "&verificationCode=" + message.get("verificationCode");

            Context context = new Context();
            context.setVariable("fullName", message.get("fullName"));
            context.setVariable("code", message.get("verificationCode"));

            mimeMessage.setContent(templateEngine.process("verificationCode", context), "text/html");
            helper.setTo(message.get("email"));
            helper.setSubject("Pryme Time email verification");
            helper.setFrom(new InternetAddress(from, name));

            mailSender.send(mimeMessage);

            LOGGER.info("A email verification message has been sent to " + message.get("email"));
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }


}