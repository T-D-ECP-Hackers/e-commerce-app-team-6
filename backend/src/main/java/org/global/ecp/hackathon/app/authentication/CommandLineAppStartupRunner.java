package org.global.ecp.hackathon.app.authentication;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.user.User;
import org.global.ecp.hackathon.app.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private static final String ADMIN = "admin";
    private final UserRepository userRepository;

    public CommandLineAppStartupRunner(final UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        final var adminUser = getAdminUser();
        log.info("Admin user created with following credentials - username: {} password: {}",
                 adminUser.getUsername(),
                 adminUser.getPassword());
    }

    private User getAdminUser() {
        final var optionalAdminUser = userRepository.getUserByUsername(ADMIN);
        if (optionalAdminUser.isEmpty()) {
            User user = User.builder().username(ADMIN).password(ADMIN).email("admin@email.com").build();
            return userRepository.save(user);
        }
        return optionalAdminUser.get();
    }
}