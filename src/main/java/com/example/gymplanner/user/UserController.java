// In UserController.java
package com.example.gymplanner.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@AuthenticationPrincipal User user) {
        // Converte l'entità User in un DTO sicuro e lo restituisce
        UserDto userDto = new UserDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return ResponseEntity.ok(userDto);
    }
}