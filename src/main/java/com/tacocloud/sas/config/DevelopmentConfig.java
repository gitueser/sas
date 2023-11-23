package com.tacocloud.sas.config;

import com.tacocloud.sas.data.UserRepository;
import com.tacocloud.sas.entity.User;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Profile("!prod")
@Configuration
public class DevelopmentConfig {
    @Bean
    public ApplicationRunner dataLoader(
            UserRepository userRepo,
            BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            userRepo.deleteAll();
            initDefaultUser(userRepo, passwordEncoder);
        };
    }

    private static void initDefaultUser(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
        userRepo.save(new User("user", passwordEncoder.encode("ppp"), "ROLE_ADMIN"));
        userRepo.save(new User("habuma", passwordEncoder.encode("password"), "ROLE_ADMIN"));
        userRepo.save(new User("tacochef", passwordEncoder.encode("password"), "ROLE_ADMIN"));
    }
}
