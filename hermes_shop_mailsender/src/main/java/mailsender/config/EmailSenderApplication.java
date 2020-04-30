package mailsender.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class EmailSenderApplication {
    public static void main(String[] args) {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(timeZone);

        Locale.setDefault(Locale.US);

        SpringApplication.run(EmailSenderApplication.class, args);
    }
}
