package com.fcv.expressCourier.controller;

import com.fcv.expressCourier.dao.RoleRepository;
import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.models.Role;
import com.fcv.expressCourier.models.RoleName;
import com.fcv.expressCourier.models.User;
import com.fcv.expressCourier.payload.APIResponse;
import com.fcv.expressCourier.payload.JwtAuthenticationResponse;
import com.fcv.expressCourier.payload.LoginRequest;
import com.fcv.expressCourier.payload.SignUpRequest;
import com.fcv.expressCourier.security.JwtTokenProvider;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
public class AuthController {
    private final
    AuthenticationManager authenticationManager;

    private final
    JwtTokenProvider jwtTokenProvider;

    private final
    UserRepository userRepository;

    private final
    PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getNameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByName(signUpRequest.getName())) {
            return new ResponseEntity(new APIResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new APIResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(),signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                // orElse and orElseGet is different:
                // orElse will be executed even if Option<T> has the element
                // which is why there's duplicate data insertion
                .orElseGet(() -> roleRepository.save(new Role(RoleName.ROLE_USER)));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);
        //TODO: add redirect
        return ResponseEntity.accepted().body(new APIResponse(true, "User registered successfully"));
    }
}

