package ru.bogdsvn.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdsvn.auth.errors.UsernameExistException;
import ru.bogdsvn.auth.store.entities.UserEntity;
import ru.bogdsvn.auth.store.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameExistException("Username already exists");
        }
        userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }
}
