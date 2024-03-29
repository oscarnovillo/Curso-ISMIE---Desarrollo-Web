package com.example.demo.ui.auth;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;


    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {

        Authentication auth =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(auth);
        request.getSession().setAttribute("LOGIN", auth);

        return username;
    }

    @GetMapping("/loginBasic")
    public String loginBasic(Principal principal, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        request.getSession().setAttribute("LOGIN", auth);

        return principal.getName();
    }


    @GetMapping("/loginToken")
    public AuthenticationResponse loginToken(@RequestBody AuthenticationRequest requestAuth, HttpServletRequest request) {
        return authenticationService.authenticate(requestAuth);
    }

}
