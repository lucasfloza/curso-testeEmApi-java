package br.com.testeemapi.api.config;

import br.com.testeemapi.api.domain.User;
import br.com.testeemapi.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        User u1 = new User(1, "Lucas", "lucas.souza@viasat.com", "123456");
        User u2 = new User(2, "Mateus", "mateus.souza@viasat.com", "789012");

        userRepository.saveAll(List.of(u1, u2));

    }

}
