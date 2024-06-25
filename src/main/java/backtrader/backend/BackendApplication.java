package backtrader.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class BackendApplication {

    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        log.info("BackTrader backend application started successfully.");
    }

    @Bean
    CommandLineRunner logging() {
        return args -> {
            try {
                log.info("Boot Time: {}, IP Address: {}", LocalDateTime.now(), InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                log.error("Failed to get local IP address", e);
            }
        };
    }
}
