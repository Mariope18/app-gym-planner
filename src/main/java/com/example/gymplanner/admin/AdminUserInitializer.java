package com.example.gymplanner.admin;

import com.example.gymplanner.user.Role;
import com.example.gymplanner.user.User;
import com.example.gymplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Controlliamo se un utente con username 'admin' esiste già per evitare di crearlo più volte
        if (userRepository.findByUsername("admin").isEmpty()) {
            // Se non esiste, lo creiamo
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin")); // Usa una password sicura!
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);

            System.out.println(">>> Utente ADMIN di default creato con successo! <<<");
        }
    }
}