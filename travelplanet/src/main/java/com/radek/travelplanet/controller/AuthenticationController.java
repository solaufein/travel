package com.radek.travelplanet.controller;

import com.radek.travelplanet.model.auth.Credentials;
import com.radek.travelplanet.model.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController()
@RequestMapping("/travel/session")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.POST)
    public User login(@RequestBody Credentials credentials, HttpSession httpSession) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = new User(credentials.getUsername(), httpSession.getId(), true);
        httpSession.setAttribute("user", user);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public User session(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
