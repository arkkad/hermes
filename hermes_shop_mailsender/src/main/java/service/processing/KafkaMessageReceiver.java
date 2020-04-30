package service.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import service.IEmailService;

import java.util.Map;

public class KafkaMessageReceiver {

    @Value("${spring.kafka.email.email.verification.code.topic}")
    private String emailVerificationCodeTopic;

    private final IEmailService IEmailService;

    @Autowired
    public KafkaMessageReceiver(IEmailService IEmailService) {
        this.IEmailService = IEmailService;
    }


    @KafkaListener(topics = "${spring.kafka.email.email.verification.code.topic}")
    public void listenEmailVerificationCodeTopic(@Payload Map<String, String> message) {
        IEmailService.sendEmailVerificationCode(message);
    }

}
