package com.example.gymplanner.admin;

import com.example.gymplanner.exercise.Exercise;
import com.example.gymplanner.exercise.ExerciseRepository;
import com.example.gymplanner.exercise.MuscleGroup;
import com.example.gymplanner.user.Role;
import com.example.gymplanner.user.User;
import com.example.gymplanner.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    // 1. Inietta l'ExerciseRepository
    @Autowired
    private ExerciseRepository exerciseRepository;

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

        // Controlliamo se un utente con username 'admin' esiste già per evitare di crearlo più volte
        if (userRepository.findByUsername("user").isEmpty()) {
            // Se non esiste, lo creiamo
            User userUser = new User();
            userUser.setUsername("user");
            userUser.setPassword(passwordEncoder.encode("user")); // Usa una password sicura!
            userUser.setRole(Role.USER);

            userRepository.save(userUser);

            System.out.println(">>> Utente USER di default creato con successo! <<<");
        }

        // 2. Logica per creare gli esercizi di default
        if (exerciseRepository.count() == 0) {
            System.out.println(">>> Creazione esercizi di default... <<<");

            Exercise panca = new Exercise("Panca Piana", "Esercizio base per il petto con bilanciere.", MuscleGroup.PETTO);
            Exercise squat = new Exercise("Squat con Bilanciere", "Esercizio fondamentale per le gambe.", MuscleGroup.GAMBE);
            Exercise stacchi = new Exercise("Stacco da Terra", "Esercizio multiarticolare per schiena e gambe.", MuscleGroup.DORSO);
            Exercise curl = new Exercise("Curl con Manubri", "Esercizio di isolamento per i bicipiti.", MuscleGroup.BICIPITI);
            Exercise frenchPress = new Exercise("French Press", "Esercizio di isolamento per i tricipiti.", MuscleGroup.TRICIPITI);
            Exercise militaryPress = new Exercise("Military Press", "Esercizio base per le spalle con bilanciere.", MuscleGroup.SPALLE);
            Exercise crunch = new Exercise("Crunch a terra", "Esercizio base per gli addominali.", MuscleGroup.ADDOME);

            exerciseRepository.saveAll(List.of(panca, squat, stacchi, curl, frenchPress, militaryPress, crunch));

            System.out.println(">>> Esercizi di default creati con successo! <<<");
        }
    }
}