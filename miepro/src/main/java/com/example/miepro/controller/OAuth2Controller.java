package com.example.miepro.controller;

import com.example.miepro.model.User;
import com.example.miepro.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class OAuth2Controller {

/*    private final UserRepository userRepository;

    public OAuth2Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
        Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();

        String id = (String) attributes.get("sub");
        String username = (String) attributes.get("name");
        String email = (String) attributes.get("email");*/

/*        User user = userRepository.findById(id)
                .orElseGet(User::new);*/
/*        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        userRepository.save(user);*/

/*        return attributes;
    }*/


}
