package com.fcv.expressCourier.security;


import com.fcv.expressCourier.dao.UserRepository;
import com.fcv.expressCourier.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final
    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u =userRepository.findByNameOrEmail(s,s)
                .orElseThrow(()->
                        new UsernameNotFoundException("User not found with username or email : " + s));
        return UserPrincipal.create(u);
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        User u = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(u);
    }
}
