package com.radek.travelplanet.controller;

import com.radek.travelplanet.controller.model.Credentials;
import com.radek.travelplanet.controller.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/travel/session")
public class AuthenticationController {

    private static final String USER_ATTRIBUTE = "user";

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public User login(@RequestBody Credentials credentials, HttpSession httpSession) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = new User(credentials.getUsername(), httpSession.getId(), true);
        httpSession.setAttribute(USER_ATTRIBUTE, user);
        return user;
    }

    @GetMapping
    public User session(HttpSession session) {
        return (User) session.getAttribute(USER_ATTRIBUTE);
    }

    @DeleteMapping
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
