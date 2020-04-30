package service;

import java.util.Map;

public interface IEmailService {
    void sendEmailVerificationCode(Map<String, String> message);
}
