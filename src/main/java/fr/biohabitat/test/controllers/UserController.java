package fr.biohabitat.test.controllers;

import fr.biohabitat.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signIn")
    public String signIn() {

        return "signIn";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/signUp/registration")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        String token = userService.register(email, password, confirmPassword);
        if (token == null) {
            model.addAttribute("error", "Registration failed");
            return "signUp";
        }

        // Sauvegarder ou utiliser le token selon le besoin (ex: dans un cookie, dans la session, etc.)
        model.addAttribute("token", token);

        return "redirect:/index"; // ou rediriger vers une autre page
    }
}
