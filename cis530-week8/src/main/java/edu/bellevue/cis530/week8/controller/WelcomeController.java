package edu.bellevue.cis530.week8.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//Controller for handling welcome page that displays user the profile.
@Controller
public class WelcomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        return "redirect:/welcomeprofile";
    }

    @GetMapping("/welcomeprofile")
    public String welcomeProfile(Authentication authentication, Model model) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof OidcUser user) {
            model.addAttribute("username",
                    user.getFullName() != null
                            ? user.getFullName()
                            : authentication.getName());
            model.addAttribute("email",
                    user.getEmail() != null
                            ? user.getEmail()
                            : "Not provided");
            model.addAttribute("provider", "Google");

        } else if (principal instanceof OAuth2User user) {
            String login = user.getAttribute("login");
            String email = user.getAttribute("email");

            model.addAttribute("username",
                    login != null ? login : authentication.getName());
            model.addAttribute("email",
                    email != null ? email : "Not provided by provider");
            model.addAttribute("provider", "GitHub");

        } else {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("email", "Not available");
            model.addAttribute("provider", "Username and password");
        }

        return "welcomeprofile";
    }
}
