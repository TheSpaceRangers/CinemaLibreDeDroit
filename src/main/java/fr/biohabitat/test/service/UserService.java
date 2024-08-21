package fr.biohabitat.test.service;

import fr.biohabitat.test.entities.User;
import fr.biohabitat.test.repository.UserRepository;
import fr.biohabitat.test.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(String email, String password, String confirmPassword) {
        if (email == null || email.isEmpty()) {
            return "Email is required";
        }

        if (password == null || password.isEmpty()) {
            return "Password is required";
        }

        if (!password.equals(confirmPassword)) {
            return "Passwords do not match";
        }

        if (userRepository.findByEmail(email) != null) {
            return "Email is already in use";
        }

        // Création et enregistrement de l'utilisateur
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        // Génération du token JWT
        String token = jwtUtil.generateToken(email);

        // Retour du token (dans un vrai contexte, on pourrait aussi retourner le user)
        return token;
    }
}
