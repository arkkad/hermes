package maestro.sevices.processing;

import maestro.model.User;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.Map;
@Component
public class KafkaMessageProducer {
    @Value("${spring.kafka.email.verification.code.topic}")
    private String emailVerificationCodeTopic;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageProducer.class);

    private final KafkaSender<String, Object> kafkaSender;

    @Autowired
    public KafkaMessageProducer(KafkaSender<String, Object> kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    public void sendEmailVerificationCode(User user, String verificationCode) {
        Map<String, String> message = new HashMap<>();
//        message.put("email", user.getEmail());
//        message.put("fullName", user.getFullName());
        message.put("verificationCode", verificationCode);

        sendData(emailVerificationCodeTopic, message, Map.class);
    }



    private void sendData(String kafkaTopic, Object value, Class clazz) {
        kafkaSender
                .send(Mono.just(SenderRecord.create(new ProducerRecord<>(kafkaTopic, value), clazz.getSimpleName())))
                .doOnError(e -> LOGGER.error("Send " + clazz.getSimpleName() + " failed", e))
                .subscribe(r -> LOGGER.info(String.format("Message %s sent successfully, topic-partition=%s-%d offset=%d",
                        r.correlationMetadata(),
                        r.recordMetadata().topic(),
                        r.recordMetadata().partition(),
                        r.recordMetadata().offset())));
    }
}
